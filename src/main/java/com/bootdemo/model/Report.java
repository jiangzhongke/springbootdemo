package com.bootdemo.model;
/***************************************
 * @Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。
	@Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。
	@Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。
	@CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。
	@GeoSpatialIndexed - 声明该字段为地理信息的索引。
	@Transient - 映射忽略的字段，该字段不会保存到
 	* 
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
public class Report {

    @Id
    private String id;

    @Indexed
    private String date;
    @Indexed
    private String content;
    @Indexed
    private String title;

    public Report() {

    }

    public Report(String date, String title, String content) {
        this.date = date;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String dateStr) {
        this.date = dateStr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Report[id=%s, date='%s', content='%s', title='%s']", id, date, content, title);
    }
}