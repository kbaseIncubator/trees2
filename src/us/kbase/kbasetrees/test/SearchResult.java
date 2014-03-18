package us.kbase.kbasetrees.test;

import java.util.List;

/**
 * A simple POJO that describes a Search Result.
 * This version is specific for getting results of a Genome search.
 * Eventually, this will likely be supplanted by a proper Search API, but
 * it is useful for looking up genome data for now.
 * @author wjriehl
 *
 */
public class SearchResult {
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Facets getFacets() {
		return facets;
	}

	public void setFacets(Facets facets) {
		this.facets = facets;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public List<GenomeItem> getItems() {
		return items;
	}

	public void setItems(List<GenomeItem> items) {
		this.items = items;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public Navigation getNavigation() {
		return navigation;
	}

	public void setNavigation(Navigation navigation) {
		this.navigation = navigation;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	private int currentPage;
	private Facets facets;
	private int itemCount;
	private List<GenomeItem> items;
	private int itemsPerPage;
	private Navigation navigation;
	private long totalResults;
	
	public class Facets {
		public List<?> getComplete() {
			return complete;
		}
		public void setComplete(List<?> complete) {
			this.complete = complete;
		}
		public List<?> getDomain() {
			return domain;
		}
		public void setDomain(List<?> domain) {
			this.domain = domain;
		}
		private List<?> complete;
		private List<?> domain;
	};
	
	public class Navigation {
		private String first;
		private String last;
		private String next;
		private String self;
		private String previous;

		public String getFirst() {
			return first;
		}
		public void setFirst(String first) {
			this.first = first;
		}
		public String getLast() {
			return last;
		}
		public void setLast(String last) {
			this.last = last;
		}
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}
		public String getSelf() {
			return self;
		}
		public void setSelf(String self) {
			this.self = self;
		}
		public String getPrevious() {
			return previous;
		}
		public void setPrevious(String previous) {
			this.previous = previous;
		}
	};
	
}
