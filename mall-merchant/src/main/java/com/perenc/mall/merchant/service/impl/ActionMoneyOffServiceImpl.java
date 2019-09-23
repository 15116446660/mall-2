package com.perenc.mall.merchant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.perenc.mall.common.constant.CommonFiledConstants;
import com.perenc.mall.common.constant.PunctuationConstants;
import com.perenc.mall.common.constant.SelectGoodsTypeConstants;
import com.perenc.mall.common.context.BaseContextHandler;
import com.perenc.mall.common.exception.BusinessException;
import com.perenc.mall.common.service.BaseService;
import com.perenc.mall.common.util.EntityUtils;
import com.perenc.mall.common.util.ListUtils;
import com.perenc.mall.common.vo.PageVO;
import com.perenc.mall.merchant.entity.dto.ActionMoneyOffDTO;
import com.perenc.mall.merchant.entity.model.ActionMoneyOffDO;
import com.perenc.mall.merchant.entity.model.GoodsDO;
import com.perenc.mall.merchant.entity.model.RelatedActionMoneyOffGoodsDO;
import com.perenc.mall.merchant.entity.vo.ActionMoneyOffVO;
import com.perenc.mall.merchant.entity.vo.GoodsVO;
import com.perenc.mall.merchant.mapper.ActionMoneyOffMapper;
import com.perenc.mall.merchant.mapper.GoodsMapper;
import com.perenc.mall.merchant.mapper.RelatedActionMoneyOffGoodsMapper;
import com.perenc.mall.merchant.service.IActionMoneyOffService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: ActionMoneyOffServiceImpl
 * @Description:
 *
 * @Author: GR
 * @Date: 2019/9/23 15:55 
 *
 * Modification History:
 * Date         Author      Description
 *---------------------------------------------------------*
 * 2019/9/23     GR     		
 */
@Slf4j
@Service
@Transactional(rollbackFor = BusinessException.class)
public class ActionMoneyOffServiceImpl extends BaseService<ActionMoneyOffMapper, ActionMoneyOffDO> implements IActionMoneyOffService {

    @Autowired
    private RelatedActionMoneyOffGoodsMapper relatedActionMoneyOffGoodsMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public void saveActionMoneyOff(ActionMoneyOffDTO actionMoneyOffDTO) {
        if (!StringUtils.isBlank(actionMoneyOffDTO.getName())) {
            ActionMoneyOffDO actionMoneyOffDO = super.getEntityOne(new QueryWrapper<ActionMoneyOffDO>()
                    .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                    .eq(CommonFiledConstants.FILED_NAME, actionMoneyOffDTO.getName()));
            if (null != actionMoneyOffDO) {
                throw new BusinessException("当前活动名称已存在，请重新输入");
            }
        }
        ActionMoneyOffDO actionMoneyOffDO = ActionMoneyOffDO.build();
        BeanUtils.copyProperties(actionMoneyOffDTO, actionMoneyOffDO);
        super.saveEntity(actionMoneyOffDO);

        // 活动针对所有商品，直接存储该活动信息
        if (SelectGoodsTypeConstants.ALL_GOODS == actionMoneyOffDO.getGoodsType()) {
            return;
        }

        // 获取所有关联的商品ID
        List<Integer> goodsIdList = ListUtils.getIntegerListByString(actionMoneyOffDTO.getGoodsList(),
                PunctuationConstants.COMMAS);
        goodsIdList.forEach(goodsId -> {
            // 创建实体对象进行存储
            RelatedActionMoneyOffGoodsDO relatedActionMoneyOffGoodsDO = RelatedActionMoneyOffGoodsDO.build()
                    .setStoreId(BaseContextHandler.getStoreId())
                    .setActionMoneyOffId(actionMoneyOffDO.getId())
                    .setGoodsId(goodsId);
            EntityUtils.build().setCreateInfo(relatedActionMoneyOffGoodsDO);
            relatedActionMoneyOffGoodsMapper.insert(relatedActionMoneyOffGoodsDO);
        });

    }

    @Override
    public ActionMoneyOffVO getActionMoneyOff(Integer id) {
        ActionMoneyOffDO actionMoneyOffDO = super.getEntityById(id);
        if (null == actionMoneyOffDO) {
            throw new BusinessException("当前满减活动ID不存在，请输入正确的满减活动ID");
        }

        ActionMoneyOffVO actionMoneyOffVO = ActionMoneyOffVO.build();
        BeanUtils.copyProperties(actionMoneyOffDO, actionMoneyOffVO);
        return actionMoneyOffVO;
    }

    @Override
    public void removeActionMoneyOffById(Integer id) {
        ActionMoneyOffDO actionMoneyOffDO = super.getEntityById(id);
        if (null == actionMoneyOffDO) {
            throw new BusinessException("当前活动ID不存在");
        }

        relatedActionMoneyOffGoodsMapper.delete(new QueryWrapper<RelatedActionMoneyOffGoodsDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_ACTION_MONEY_OFF_ID, id));

        super.removeEntityById(id);
    }

    @Override
    public PageVO listActionMoneyOffs(Integer currentPage, Integer pageSize) {

        QueryWrapper<ActionMoneyOffDO> actionMoneyOffDOQueryWrapper = new QueryWrapper<ActionMoneyOffDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId());
        IPage<ActionMoneyOffDO> actionMoneyOffDOIPage = super.listEntitysByPage(new Page(currentPage, pageSize), actionMoneyOffDOQueryWrapper);

        List<ActionMoneyOffDO> actionMoneyOffDOList = actionMoneyOffDOIPage.getRecords();
        List<ActionMoneyOffVO> actionMoneyOffVOList = new ArrayList<>();
        actionMoneyOffDOList.forEach(actionMoneyOffDO -> {
            ActionMoneyOffVO actionMoneyOffVO = ActionMoneyOffVO.build();
            BeanUtils.copyProperties(actionMoneyOffDO, actionMoneyOffVO);
            actionMoneyOffVOList.add(actionMoneyOffVO);
        });

        PageVO<ActionMoneyOffVO> pageVO = PageVO.<ActionMoneyOffVO>build().setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(actionMoneyOffVOList)
                .setTotal(super.count(new QueryWrapper<ActionMoneyOffDO>()
                        .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())));
        return pageVO;
    }

    @Override
    public PageVO listActionMoneyOffs(Integer actionId, Integer currentPage, Integer pageSize) {

        QueryWrapper<RelatedActionMoneyOffGoodsDO> actionMoneyOffGoodsDOQueryWrapper = new QueryWrapper<RelatedActionMoneyOffGoodsDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_ID, actionId);

        IPage<RelatedActionMoneyOffGoodsDO> relatedActionMoneyOffGoodsDOIPage = relatedActionMoneyOffGoodsMapper.selectPage(new Page<RelatedActionMoneyOffGoodsDO>(currentPage, pageSize),
                actionMoneyOffGoodsDOQueryWrapper);

        List<RelatedActionMoneyOffGoodsDO> relatedActionMoneyOffGoodsDOList = relatedActionMoneyOffGoodsDOIPage.getRecords();

        List<Integer> goodsIdList = new ArrayList<>();
        relatedActionMoneyOffGoodsDOList.forEach(actionMoneyOffDO -> goodsIdList.add(actionMoneyOffDO.getGoodsId()));

        List<GoodsDO> goodsDOList = goodsMapper.selectBatchIds(goodsIdList);
        List<GoodsVO> goodsVOList = new ArrayList<>();
        goodsDOList.forEach(goodsDO -> {
            GoodsVO goodsVO = GoodsVO.build();
            BeanUtils.copyProperties(goodsDO, goodsVO);
            goodsVOList.add(goodsVO);
        });

        PageVO<GoodsVO> goodsVOPageVO = PageVO.<GoodsVO>build()
                .setCurrentPage(currentPage)
                .setPageSize(pageSize)
                .setList(goodsVOList)
                .setTotal(relatedActionMoneyOffGoodsMapper.selectCount(new QueryWrapper<RelatedActionMoneyOffGoodsDO>()
                        .eq(CommonFiledConstants.FILED_ID, actionId)));

        return goodsVOPageVO;
    }

    @Override
    public void updateActionMoneyOff(ActionMoneyOffDTO actionMoneyOffDTO) {
        if (null == actionMoneyOffDTO.getId()) {
            throw new BusinessException("当前ID不能为空");
        }

        // 获取当前角色对象
        ActionMoneyOffDO actionMoneyOffDO = super.getEntityById(actionMoneyOffDTO.getId());
        if (null == actionMoneyOffDO) {
            throw new BusinessException("当前ID不存在，请输入正确的活动ID");
        }

        List<RelatedActionMoneyOffGoodsDO> relatedActionMoneyOffGoodsDOList = relatedActionMoneyOffGoodsMapper.selectList(new QueryWrapper<RelatedActionMoneyOffGoodsDO>()
                .eq(CommonFiledConstants.FILED_STORE_ID, BaseContextHandler.getStoreId())
                .eq(CommonFiledConstants.FILED_ACTION_MONEY_OFF_ID, actionMoneyOffDO.getId()));
        // 原有关联的商品数据
        List<Integer> oldGoodsIdList = new ArrayList<>();
        relatedActionMoneyOffGoodsDOList.forEach(relatedActionMoneyOffGoodsDO -> oldGoodsIdList.add(relatedActionMoneyOffGoodsDO.getGoodsId()));
        // 获取新上传的商品ID列表
        List<Integer> newGoodsIdList = ListUtils.getIntegerListByString(actionMoneyOffDTO.getGoodsList(), PunctuationConstants.COMMAS);
        // 过滤要删除的商品ID
        Set<Integer> deleteGoodsIdList = ListUtils.getNotExistBySource(newGoodsIdList, oldGoodsIdList);
        // 过滤要新增插入的商品ID
        Set<Integer> insertGoodsIdList = ListUtils.getNotExistBySource(oldGoodsIdList, newGoodsIdList);
        // 根据商品ID删除指定数据
        if (deleteGoodsIdList.size() > 0) {
            relatedActionMoneyOffGoodsMapper.delete(new QueryWrapper<RelatedActionMoneyOffGoodsDO>()
                    .eq(CommonFiledConstants.FILED_ACTION_MONEY_OFF_ID, actionMoneyOffDTO.getId())
                    .in(CommonFiledConstants.FILED_MENU_ID, deleteGoodsIdList));
        }

        // 遍历插入
        insertGoodsIdList.forEach(goodId -> {
            RelatedActionMoneyOffGoodsDO relatedRoleMenuDO = RelatedActionMoneyOffGoodsDO.build()
                    .setActionMoneyOffId(actionMoneyOffDTO.getId())
                    .setGoodsId(goodId);
            EntityUtils.build().setCreateInfo(relatedRoleMenuDO);
            relatedActionMoneyOffGoodsMapper.insert(relatedRoleMenuDO);
        });

        super.updateEntity(actionMoneyOffDO);
    }
}
