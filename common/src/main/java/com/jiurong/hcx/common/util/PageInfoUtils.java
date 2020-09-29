package com.jiurong.hcx.common.util;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hcx
 * @date 2019/3/28
 * explain: 分析信息拷贝工具类
 */
public class PageInfoUtils {

    /**
     * 复制
     *
     * @param source      源分页数据
     * @param targetClass 目标类
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> PageInfo<K> copy(PageInfo<T> source, Class<K> targetClass) {
        List<T> sourceList = source.getList();
        List<K> targetList = new ArrayList<>();
        sourceList.forEach(t -> {
            K k = null;
            try {
                k = targetClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (k != null) {
                BeanUtils.copyProperties(t, k);
                targetList.add(k);
            }
        });
        PageInfo<K> target = new PageInfo<>(targetList);
        //分页信息
        target.setTotal(source.getTotal());
        target.setPageNum(source.getPageNum());
        target.setPageSize(source.getPageSize());
        target.setSize(source.getSize());
        target.setStartRow(source.getStartRow());
        target.setEndRow(source.getEndRow());
        target.setPages(source.getPages());
        target.setPrePage(source.getPrePage());
        target.setNextPage(source.getNextPage());
        target.setIsFirstPage(source.isIsFirstPage());
        target.setIsLastPage(source.isIsLastPage());
        target.setHasPreviousPage(source.isHasPreviousPage());
        target.setHasNextPage(source.isHasNextPage());
        target.setNavigatePages(source.getNavigatePages());
        target.setNavigatepageNums(source.getNavigatepageNums());
        target.setNavigateFirstPage(source.getNavigateFirstPage());
        target.setNavigateLastPage(source.getNavigateLastPage());
        return target;
    }

    /**
     * 复制分页数据
     *
     * @param source 拷贝源
     * @param target 拷贝目标
     */
    public static void copy(PageInfo source, PageInfo target) {
        target.setPageNum(source.getPageNum());
        target.setPageSize(source.getPageSize());
        target.setSize(source.getSize());
        target.setStartRow(source.getStartRow());
        target.setEndRow(source.getEndRow());
        target.setPages(source.getPages());
        target.setPrePage(source.getPrePage());
        target.setNextPage(source.getNextPage());
        target.setIsFirstPage(source.isIsFirstPage());
        target.setIsLastPage(source.isIsFirstPage());
        target.setHasPreviousPage(source.isHasPreviousPage());
        target.setHasNextPage(source.isHasNextPage());
        target.setNavigatePages(source.getNavigatePages());
        target.setNavigatepageNums(source.getNavigatepageNums());
        target.setNavigateFirstPage(source.getNavigateFirstPage());
        target.setNavigateLastPage(source.getNavigateLastPage());
        target.setTotal(source.getTotal());
    }

    /**
     * 获取页数
     *
     * @param pageSize 每页的数量
     * @param total    总记录数
     * @return 总页数
     */
    public static int getPages(int pageSize, int total) {
        if (pageSize > 0) {
            return (total / pageSize + (total % pageSize == 0 ? 0 : 1));
        } else {
            return 0;
        }
    }

    public static <T> PageInfo<T> generatePage(Integer pageNum, Integer pageSize, List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) {
            pageNum = 1;
        }
        if (pageSize > list.size()) {
            pageSize = list.size();
        }
        int pages = getPages(pageSize, list.size());
        //获取当前页的列表
        int toIndex = pageNum * pageSize;
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        int fromIndex = pageNum * pageSize - pageSize;
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (fromIndex > list.size()) {
            fromIndex = list.size();
        }
        List<T> resultList = list.subList(fromIndex, toIndex);
        //列表
        pageInfo.setList(resultList);
        //第几页
        pageInfo.setPageNum(pageNum);
        //每页数量
        pageInfo.setPageSize(pageSize);
        //当前页数量
        pageInfo.setSize(resultList.size());
        int startRow = 1;
        if (resultList.size() == 0) {
            startRow = 0;
        }
        //起始行
        pageInfo.setStartRow(startRow);
        //结束行
        pageInfo.setEndRow(resultList.size());
        //总页数
        pageInfo.setPages(pages);
        //前一页
        int prePage = pageNum - 1;
        pageInfo.setPrePage(prePage);
        //后一页
        int nextPage = pageNum + 1;
        pageInfo.setNextPage(nextPage);
        //是否第一页
        pageInfo.setIsFirstPage(pageNum == 1);
        //是否最后一页
        pageInfo.setIsLastPage(pageNum == pages || pages == 0);
        //是否有前一页
        pageInfo.setHasPreviousPage(pageNum > 1);
        //是否有后一页
        pageInfo.setHasNextPage(pageNum < pages);
        //导航页
        int navigatePages = 8;
        pageInfo.setNavigatePages(navigatePages);
        //当总页数小于或等于导航页码数时
        int[] navigatepageNums;
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //当总页数大于导航页码数时
            navigatepageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;
            if (startNum < 1) {
                startNum = 1;
                //(最前navigatePages页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //最后navigatePages页
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //所有中间页
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
        int navigateFirstPage = 1;
        int navigateLastPage = 1;
        if (navigatepageNums.length > 0) {
            navigateFirstPage = navigatepageNums[0];
            navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
        }
        pageInfo.setNavigatepageNums(navigatepageNums);
        pageInfo.setNavigateFirstPage(navigateFirstPage);
        pageInfo.setNavigateLastPage(navigateLastPage);
        pageInfo.setTotal(list.size());
        return pageInfo;
    }

}
