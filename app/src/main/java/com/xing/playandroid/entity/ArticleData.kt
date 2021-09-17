package com.xing.playandroid.entity

/**
 *
 * @ProjectName: mvvmdemo
 * @Description: 作用描述 <todo>
 * @Author: xinxing.tao
 * @CreateDate: 2021/5/24 11:20
 * @UpdateUser: xinxing.tao
 * @UpdateDate: 2021/5/24 11:20
 * @UpdateRemark: 无
 */
data class ArticleData(
    val curPage: Int?,
    val datas: List<Article>?,
    val offset: Int?,
    val over: Boolean?,
    val pageCount: Int?,
    val size: Int?,
    val total: Int?
)


data class Article(
    val apkLink: String? = null,
    val audit: Int? = null,
    val author: String? = null,
    val canEdit: Boolean? = null,
    val chapterId: Int? = null,
    val chapterName: String? = null,
    val collect: Boolean? = null,
    val courseId: Int? = null,
    val desc: String? = null,
    val descMd: String? = null,
    val envelopePic: String? = null,
    val fresh: Boolean? = null,
    val host: String? = null,
    val id: Int? = null,
    val link: String? = null,
    val niceDate: String? = null,
    val niceShareDate: String? = null,
    val origin: String? = null,
    val prefix: String? = null,
    val projectLink: String? = null,
    val publishTime: Long? = null,
    val realSuperChapterId: Int? = null,
    val selfVisible: Int? = null,
    val shareDate: Long? = null,
    val shareUser: String? = null,
    val superChapterId: Int? = null,
    val superChapterName: String? = null,
    val tags: List<Tag>? = null,
    val title: String,
    val type: Int? = null,
    val userId: Int? = null,
    val visible: Int? = null,
    val zan: Int? = null
)

data class Tag(
    val name: String,
    val url: String
)