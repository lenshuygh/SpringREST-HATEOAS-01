package hateoas.bookmarks;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BookmarkResource extends ResourceSupport{
	private final Bookmark bookmark;
	
	public BookmarkResource(Bookmark bookmark){
		String userName = bookmark.getAccount().getUsername();
		this.bookmark = bookmark;
		this.add(new Link(bookmark.getUri(), "bookmark-uri"));
		this.add(linkTo(BookmarkRestController.class,userName).withRel("bookmarks"));
		this.add(linkTo(methodOn(BookmarkRestController.class, userName)
				.readBookmark(userName, bookmark.getId())).withSelfRel());
	}
	
	public Bookmark getBookmark(){
		return bookmark;
	}
}
