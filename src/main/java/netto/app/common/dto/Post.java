package netto.app.common.dto;

import java.util.List;

public class Post implements Comparable<Post> {

	private Integer id;
	private String author;
	private Integer authorId;	
	private Integer likes;
	private Double popularity;
	private Integer reads;
	private List<String> tags;
	
	public Post() {
	}
	
	public Post(Integer id, String author, Integer authorId, Integer likes, Double popularity, Integer reads) {
		this.id=id;
		this.author=author;
		this.authorId=authorId;	
		this.likes=likes;
		this.popularity=popularity;
		this.reads=reads;
		//this.tags;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	public Integer getReads() {
		return reads;
	}

	public void setReads(Integer reads) {
		this.reads = reads;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("[id:").append(id)
				.append(", author:").append(author)
				.append(", authorId:").append(authorId)
				.append(", likes:").append(likes)
				.append(", popularity:").append(popularity)
				.append(", reads:").append(reads)
				.append(tags!=null?", tags:":"").append(tags!=null?tags:"")
				.append("]").toString();
	}

	@Override
	public int compareTo(Post p) {		
		return this.getId().compareTo( p.getId() );
	} 
	
}
