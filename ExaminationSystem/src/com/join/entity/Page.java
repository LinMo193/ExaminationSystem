package com.join.entity;

import java.util.List;

public class Page {
    //    当前页
    private int currentPage;
    //    获取总数据
    private int totalCount;
    //    获取总页数
    private int totalPage;
    //    获取当页要显示的单选题
    private List<ExamPageSingle> singleList;
    //    获取当页要显示的填空题
    private List<ExamPageCompletion> completionList;
    //    获取当页要显示的简答题
    private List<ExamPageShort> shortList;

    public Page() {
    }

    public Page(int currentPage, int totalCount, int totalPage, List<ExamPageSingle> singleList, List<ExamPageCompletion> completionList, List<ExamPageShort> shortList) {
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.singleList = singleList;
        this.completionList = completionList;
        this.shortList = shortList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = this.totalCount%5==0?this.totalCount/5:this.totalCount/5+1;
    }

    public List<ExamPageSingle> getSingleList() {
        return singleList;
    }

    public void setSingleList(List<ExamPageSingle> singleList) {
        this.singleList = singleList;
    }

    public List<ExamPageCompletion> getCompletionList() {
        return completionList;
    }

    public void setCompletionList(List<ExamPageCompletion> completionList) {
        this.completionList = completionList;
    }

    public List<ExamPageShort> getShortList() {
        return shortList;
    }

    public void setShortList(List<ExamPageShort> shortList) {
        this.shortList = shortList;
    }
}
