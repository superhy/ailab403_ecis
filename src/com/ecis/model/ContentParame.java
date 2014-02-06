package com.ecis.model;

public class ContentParame {

	private String baNameQuery; // 贴吧名cssQuery
	private String titleQuery; // 贴吧标题cssQuery
	private String pagerQuery; // 分页信息cssQuery
	private String fetchPagerMethod; // 贴吧分页url获取方法名（反射机制）
	private String postDivQuery; // 帖子主体div的cssQuery
	private String postContentQuery; // 帖子内容cssQuery
	private String postAuthorQuery; // 发帖作者cssQuery
	private String postTimeQuery; // 发帖时间cssQuery
	private String replyDivQuery; // 回复主体div的cssQuery
	private String replyContentQuery; // 回复信息cssQuery
	private String replyAuthorQuery; // 回复作者cssQuery
	private String replyTimeQuery; // 回复时间cssQuery

	public ContentParame() {
	}

	public ContentParame(String baNameQuery, String titleQuery,
			String pagerQuery, String fetchPagerMethod, String postDivQuery,
			String postContentQuery, String postAuthorQuery,
			String postTimeQuery, String replyDivQuery,
			String replyContentQuery, String replyAuthorQuery,
			String replyTimeQuery) {
		super();
		this.baNameQuery = baNameQuery;
		this.titleQuery = titleQuery;
		this.pagerQuery = pagerQuery;
		this.fetchPagerMethod = fetchPagerMethod;
		this.postDivQuery = postDivQuery;
		this.postContentQuery = postContentQuery;
		this.postAuthorQuery = postAuthorQuery;
		this.postTimeQuery = postTimeQuery;
		this.replyDivQuery = replyDivQuery;
		this.replyContentQuery = replyContentQuery;
		this.replyAuthorQuery = replyAuthorQuery;
		this.replyTimeQuery = replyTimeQuery;
	}

	public String getBaNameQuery() {
		return baNameQuery;
	}

	public void setBaNameQuery(String baNameQuery) {
		this.baNameQuery = baNameQuery;
	}

	public String getTitleQuery() {
		return titleQuery;
	}

	public void setTitleQuery(String titleQuery) {
		this.titleQuery = titleQuery;
	}

	public String getPagerQuery() {
		return pagerQuery;
	}

	public void setPagerQuery(String pagerQuery) {
		this.pagerQuery = pagerQuery;
	}

	public String getFetchPagerMethod() {
		return fetchPagerMethod;
	}

	public void setFetchPagerMethod(String fetchPagerMethod) {
		this.fetchPagerMethod = fetchPagerMethod;
	}

	public String getPostDivQuery() {
		return postDivQuery;
	}

	public void setPostDivQuery(String postDivQuery) {
		this.postDivQuery = postDivQuery;
	}

	public String getPostContentQuery() {
		return postContentQuery;
	}

	public void setPostContentQuery(String postContentQuery) {
		this.postContentQuery = postContentQuery;
	}

	public String getPostAuthorQuery() {
		return postAuthorQuery;
	}

	public void setPostAuthorQuery(String postAuthorQuery) {
		this.postAuthorQuery = postAuthorQuery;
	}

	public String getPostTimeQuery() {
		return postTimeQuery;
	}

	public void setPostTimeQuery(String postTimeQuery) {
		this.postTimeQuery = postTimeQuery;
	}

	public String getReplyDivQuery() {
		return replyDivQuery;
	}

	public void setReplyDivQuery(String replyDivQuery) {
		this.replyDivQuery = replyDivQuery;
	}

	public String getReplyContentQuery() {
		return replyContentQuery;
	}

	public void setReplyContentQuery(String replyContentQuery) {
		this.replyContentQuery = replyContentQuery;
	}

	public String getReplyAuthorQuery() {
		return replyAuthorQuery;
	}

	public void setReplyAuthorQuery(String replyAuthorQuery) {
		this.replyAuthorQuery = replyAuthorQuery;
	}

	public String getReplyTimeQuery() {
		return replyTimeQuery;
	}

	public void setReplyTimeQuery(String replyTimeQuery) {
		this.replyTimeQuery = replyTimeQuery;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((baNameQuery == null) ? 0 : baNameQuery.hashCode());
		result = prime
				* result
				+ ((fetchPagerMethod == null) ? 0 : fetchPagerMethod.hashCode());
		result = prime * result
				+ ((pagerQuery == null) ? 0 : pagerQuery.hashCode());
		result = prime * result
				+ ((postAuthorQuery == null) ? 0 : postAuthorQuery.hashCode());
		result = prime
				* result
				+ ((postContentQuery == null) ? 0 : postContentQuery.hashCode());
		result = prime * result
				+ ((postDivQuery == null) ? 0 : postDivQuery.hashCode());
		result = prime * result
				+ ((postTimeQuery == null) ? 0 : postTimeQuery.hashCode());
		result = prime
				* result
				+ ((replyAuthorQuery == null) ? 0 : replyAuthorQuery.hashCode());
		result = prime
				* result
				+ ((replyContentQuery == null) ? 0 : replyContentQuery
						.hashCode());
		result = prime * result
				+ ((replyDivQuery == null) ? 0 : replyDivQuery.hashCode());
		result = prime * result
				+ ((replyTimeQuery == null) ? 0 : replyTimeQuery.hashCode());
		result = prime * result
				+ ((titleQuery == null) ? 0 : titleQuery.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContentParame other = (ContentParame) obj;
		if (baNameQuery == null) {
			if (other.baNameQuery != null)
				return false;
		} else if (!baNameQuery.equals(other.baNameQuery))
			return false;
		if (fetchPagerMethod == null) {
			if (other.fetchPagerMethod != null)
				return false;
		} else if (!fetchPagerMethod.equals(other.fetchPagerMethod))
			return false;
		if (pagerQuery == null) {
			if (other.pagerQuery != null)
				return false;
		} else if (!pagerQuery.equals(other.pagerQuery))
			return false;
		if (postAuthorQuery == null) {
			if (other.postAuthorQuery != null)
				return false;
		} else if (!postAuthorQuery.equals(other.postAuthorQuery))
			return false;
		if (postContentQuery == null) {
			if (other.postContentQuery != null)
				return false;
		} else if (!postContentQuery.equals(other.postContentQuery))
			return false;
		if (postDivQuery == null) {
			if (other.postDivQuery != null)
				return false;
		} else if (!postDivQuery.equals(other.postDivQuery))
			return false;
		if (postTimeQuery == null) {
			if (other.postTimeQuery != null)
				return false;
		} else if (!postTimeQuery.equals(other.postTimeQuery))
			return false;
		if (replyAuthorQuery == null) {
			if (other.replyAuthorQuery != null)
				return false;
		} else if (!replyAuthorQuery.equals(other.replyAuthorQuery))
			return false;
		if (replyContentQuery == null) {
			if (other.replyContentQuery != null)
				return false;
		} else if (!replyContentQuery.equals(other.replyContentQuery))
			return false;
		if (replyDivQuery == null) {
			if (other.replyDivQuery != null)
				return false;
		} else if (!replyDivQuery.equals(other.replyDivQuery))
			return false;
		if (replyTimeQuery == null) {
			if (other.replyTimeQuery != null)
				return false;
		} else if (!replyTimeQuery.equals(other.replyTimeQuery))
			return false;
		if (titleQuery == null) {
			if (other.titleQuery != null)
				return false;
		} else if (!titleQuery.equals(other.titleQuery))
			return false;
		return true;
	}

}
