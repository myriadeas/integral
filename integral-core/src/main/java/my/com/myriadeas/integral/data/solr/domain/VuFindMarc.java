package my.com.myriadeas.integral.data.solr.domain;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class VuFindMarc implements SearchableVuFindMarc {
	@Field(ID)
	private String id;
	@Field(FULLRECORD)
	private String fullrecord;
	@Field(MARC_ERROR)
	private java.util.LinkedHashSet<String> marc_error;
	@Field(ALLFIELDS)
	private String allfields;
	@Field(ALLFIELDS_UNSTEMMED)
	private String allfields_unstemmed;
	@Field(FULLTEXT)
	private String fulltext;
	@Field(FULLTEXT_UNSTEMMED)
	private String fulltext_unstemmed;
	@Field(SPELLING)
	private String spelling;
	@Field(SPELLINGSHINGLE)
	private java.util.LinkedHashSet<String> spellingShingle;
	@Field(CJK)
	private java.util.LinkedHashSet<String> cjk;
	@Field(INSTITUTION)
	private java.util.LinkedHashSet<String> institution;
	@Field(COLLECTION)
	private java.util.LinkedHashSet<String> collection;
	@Field(BUILDING)
	private java.util.LinkedHashSet<String> building;
	@Field(LANGUAGE)
	private java.util.LinkedHashSet<String> language;
	@Field(FORMAT)
	private java.util.LinkedHashSet<String> format;
	@Field(AUTHOR)
	private String author;
	@Field(AUTHOR_LETTER)
	private String author_letter;
	@Field(AUTHORSTR)
	private String authorStr;
	@Field(TITLE)
	private String title;
	@Field(TITLE_SORT)
	private String title_sort;
	@Field(TITLE_SUB)
	private String title_sub;
	@Field(TITLE_SHORT)
	private String title_short;
	@Field(TITLE_FULL)
	private String title_full;
	@Field(TITLE_FULL_UNSTEMMED)
	private String title_full_unstemmed;
	@Field(TITLE_FULLSTR)
	private String title_fullStr;
	@Field(TITLE_AUTH)
	private String title_auth;
	@Field(PHYSICAL)
	private java.util.LinkedHashSet<String> physical;
	@Field(PUBLISHER)
	private java.util.LinkedHashSet<String> publisher;
	@Field(PUBLISHERSTR)
	private java.util.LinkedHashSet<String> publisherStr;
	@Field(PUBLISHDATE)
	private java.util.LinkedHashSet<String> publishDate;
	@Field(PUBLISHDATESORT)
	private String publishDateSort;
	@Field(EDITION)
	private String edition;
	@Field(DESCRIPTION)
	private String description;
	@Field(CONTENTS)
	private java.util.LinkedHashSet<String> contents;
	@Field(URL)
	private java.util.LinkedHashSet<String> url;
	@Field(THUMBNAIL)
	private String thumbnail;
	@Field(LCCN)
	private String lccn;
	@Field(CTRLNUM)
	private java.util.LinkedHashSet<String> ctrlnum;
	@Field(ISBN)
	private java.util.LinkedHashSet<String> isbn;
	@Field(ISSN)
	private java.util.LinkedHashSet<String> issn;
	@Field(OCLC_NUM)
	private java.util.LinkedHashSet<String> oclc_num;
	@Field(CALLNUMBER)
	private String callnumber;
	@Field(CALLNUMBER_A)
	private String callnumber_a;
	@Field(CALLNUMBER_FIRST)
	private String callnumber_first;
	@Field(CALLNUMBER_FIRST_CODE)
	private String callnumber_first_code;
	@Field(CALLNUMBER_SUBJECT)
	private String callnumber_subject;
	@Field(CALLNUMBER_SUBJECT_CODE)
	private String callnumber_subject_code;
	@Field(CALLNUMBER_LABEL)
	private String callnumber_label;
	@Field(DEWEY_HUNDREDS)
	private java.util.LinkedHashSet<String> dewey_hundreds;
	@Field(DEWEY_TENS)
	private java.util.LinkedHashSet<String> dewey_tens;
	@Field(DEWEY_ONES)
	private java.util.LinkedHashSet<String> dewey_ones;
	@Field(DEWEY_FULL)
	private java.util.LinkedHashSet<String> dewey_full;
	@Field(DEWEY_SORT)
	private String dewey_sort;
	@Field(DEWEY_RAW)
	private String dewey_raw;
	@Field(AUTHOR2)
	private java.util.LinkedHashSet<String> author2;
	@Field(AUTHOR2STR)
	private java.util.LinkedHashSet<String> author2Str;
	@Field(AUTHOR2_ROLE)
	private java.util.LinkedHashSet<String> author2_role;
	@Field(AUTHOR_FULLER)
	private String author_fuller;
	@Field(AUTHOR_ADDITIONAL)
	private java.util.LinkedHashSet<String> author_additional;
	@Field(AUTHOR_ADDITIONALSTR)
	private java.util.LinkedHashSet<String> author_additionalStr;
	@Field(TITLE_ALT)
	private java.util.LinkedHashSet<String> title_alt;
	@Field(TITLE_OLD)
	private java.util.LinkedHashSet<String> title_old;
	@Field(TITLE_NEW)
	private java.util.LinkedHashSet<String> title_new;
	@Field(DATESPAN)
	private java.util.LinkedHashSet<String> dateSpan;
	@Field(SERIES)
	private java.util.LinkedHashSet<String> series;
	@Field(SERIES2)
	private java.util.LinkedHashSet<String> series2;
	@Field(TOPIC)
	private java.util.LinkedHashSet<String> topic;
	@Field(TOPIC_UNSTEMMED)
	private java.util.LinkedHashSet<String> topic_unstemmed;
	@Field(TOPIC_FACET)
	private java.util.LinkedHashSet<String> topic_facet;
	@Field(TOPIC_BROWSE)
	private java.util.LinkedHashSet<String> topic_browse;
	@Field(AUTHOR_BROWSE)
	private java.util.LinkedHashSet<String> author_browse;
	@Field(GENRE)
	private java.util.LinkedHashSet<String> genre;
	@Field(GENRE_FACET)
	private java.util.LinkedHashSet<String> genre_facet;
	@Field(GEOGRAPHIC)
	private java.util.LinkedHashSet<String> geographic;
	@Field(GEOGRAPHIC_FACET)
	private java.util.LinkedHashSet<String> geographic_facet;
	@Field(ERA)
	private java.util.LinkedHashSet<String> era;
	@Field(ERA_FACET)
	private java.util.LinkedHashSet<String> era_facet;
	@Field(ILLUSTRATED)
	private String illustrated;
	@Field(LONG_LAT)
	private String long_lat;
	@Field(CONTAINER_TITLE)
	private String container_title;
	@Field(CONTAINER_VOLUME)
	private String container_volume;
	@Field(CONTAINER_ISSUE)
	private String container_issue;
	@Field(CONTAINER_START_PAGE)
	private String container_start_page;
	@Field(CONTAINER_REFERENCE)
	private String container_reference;
	@Field(RECORDTYPE)
	private String recordtype;
	@Field(FIRST_INDEXED)
	private Date first_indexed;
	@Field(LAST_INDEXED)
	private Date last_indexed;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFullrecord() {
		return fullrecord;
	}
	public void setFullrecord(String fullrecord) {
		this.fullrecord = fullrecord;
	}
	public java.util.LinkedHashSet<String> getMarc_error() {
		return marc_error;
	}
	public void setMarc_error(java.util.LinkedHashSet<String> marc_error) {
		this.marc_error = marc_error;
	}
	public String getAllfields() {
		return allfields;
	}
	public void setAllfields(String allfields) {
		this.allfields = allfields;
	}
	public String getAllfields_unstemmed() {
		return allfields_unstemmed;
	}
	public void setAllfields_unstemmed(String allfields_unstemmed) {
		this.allfields_unstemmed = allfields_unstemmed;
	}
	public String getFulltext() {
		return fulltext;
	}
	public void setFulltext(String fulltext) {
		this.fulltext = fulltext;
	}
	public String getFulltext_unstemmed() {
		return fulltext_unstemmed;
	}
	public void setFulltext_unstemmed(String fulltext_unstemmed) {
		this.fulltext_unstemmed = fulltext_unstemmed;
	}
	public String getSpelling() {
		return spelling;
	}
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}
	public java.util.LinkedHashSet<String> getSpellingShingle() {
		return spellingShingle;
	}
	public void setSpellingShingle(java.util.LinkedHashSet<String> spellingShingle) {
		this.spellingShingle = spellingShingle;
	}
	public java.util.LinkedHashSet<String> getText_cjk() {
		return cjk;
	}
	public void setText_cjk(java.util.LinkedHashSet<String> text_cjk) {
		this.cjk = text_cjk;
	}
	public java.util.LinkedHashSet<String> getInstitution() {
		return institution;
	}
	public void setInstitution(java.util.LinkedHashSet<String> institution) {
		this.institution = institution;
	}
	public java.util.LinkedHashSet<String> getCollection() {
		return collection;
	}
	public void setCollection(java.util.LinkedHashSet<String> collection) {
		this.collection = collection;
	}
	public java.util.LinkedHashSet<String> getBuilding() {
		return building;
	}
	public void setBuilding(java.util.LinkedHashSet<String> building) {
		this.building = building;
	}
	public java.util.LinkedHashSet<String> getLanguage() {
		return language;
	}
	public void setLanguage(java.util.LinkedHashSet<String> language) {
		this.language = language;
	}
	public java.util.LinkedHashSet<String> getFormat() {
		return format;
	}
	public void setFormat(java.util.LinkedHashSet<String> format) {
		this.format = format;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor_letter() {
		return author_letter;
	}
	public void setAuthor_letter(String author_letter) {
		this.author_letter = author_letter;
	}
	public String getAuthorStr() {
		return authorStr;
	}
	public void setAuthorStr(String authorStr) {
		this.authorStr = authorStr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle_sort() {
		return title_sort;
	}
	public void setTitle_sort(String title_sort) {
		this.title_sort = title_sort;
	}
	public String getTitle_sub() {
		return title_sub;
	}
	public void setTitle_sub(String title_sub) {
		this.title_sub = title_sub;
	}
	public String getTitle_short() {
		return title_short;
	}
	public void setTitle_short(String title_short) {
		this.title_short = title_short;
	}
	public String getTitle_full() {
		return title_full;
	}
	public void setTitle_full(String title_full) {
		this.title_full = title_full;
	}
	public String getTitle_full_unstemmed() {
		return title_full_unstemmed;
	}
	public void setTitle_full_unstemmed(String title_full_unstemmed) {
		this.title_full_unstemmed = title_full_unstemmed;
	}
	public String getTitle_fullStr() {
		return title_fullStr;
	}
	public void setTitle_fullStr(String title_fullStr) {
		this.title_fullStr = title_fullStr;
	}
	public String getTitle_auth() {
		return title_auth;
	}
	public void setTitle_auth(String title_auth) {
		this.title_auth = title_auth;
	}
	public java.util.LinkedHashSet<String> getPhysical() {
		return physical;
	}
	public void setPhysical(java.util.LinkedHashSet<String> physical) {
		this.physical = physical;
	}
	public java.util.LinkedHashSet<String> getPublisher() {
		return publisher;
	}
	public void setPublisher(java.util.LinkedHashSet<String> publisher) {
		this.publisher = publisher;
	}
	public java.util.LinkedHashSet<String> getPublisherStr() {
		return publisherStr;
	}
	public void setPublisherStr(java.util.LinkedHashSet<String> publisherStr) {
		this.publisherStr = publisherStr;
	}
	public java.util.LinkedHashSet<String> getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(java.util.LinkedHashSet<String> publishDate) {
		this.publishDate = publishDate;
	}
	public String getPublishDateSort() {
		return publishDateSort;
	}
	public void setPublishDateSort(String publishDateSort) {
		this.publishDateSort = publishDateSort;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public java.util.LinkedHashSet<String> getContents() {
		return contents;
	}
	public void setContents(java.util.LinkedHashSet<String> contents) {
		this.contents = contents;
	}
	public java.util.LinkedHashSet<String> getUrl() {
		return url;
	}
	public void setUrl(java.util.LinkedHashSet<String> url) {
		this.url = url;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getLccn() {
		return lccn;
	}
	public void setLccn(String lccn) {
		this.lccn = lccn;
	}
	public java.util.LinkedHashSet<String> getCtrlnum() {
		return ctrlnum;
	}
	public void setCtrlnum(java.util.LinkedHashSet<String> ctrlnum) {
		this.ctrlnum = ctrlnum;
	}
	public java.util.LinkedHashSet<String> getIsbn() {
		return isbn;
	}
	public void setIsbn(java.util.LinkedHashSet<String> isbn) {
		this.isbn = isbn;
	}
	public java.util.LinkedHashSet<String> getIssn() {
		return issn;
	}
	public void setIssn(java.util.LinkedHashSet<String> issn) {
		this.issn = issn;
	}
	public java.util.LinkedHashSet<String> getOclc_num() {
		return oclc_num;
	}
	public void setOclc_num(java.util.LinkedHashSet<String> oclc_num) {
		this.oclc_num = oclc_num;
	}
	public String getCallnumber() {
		return callnumber;
	}
	public void setCallnumber(String callnumber) {
		this.callnumber = callnumber;
	}
	public String getCallnumber_a() {
		return callnumber_a;
	}
	public void setCallnumber_a(String callnumber_a) {
		this.callnumber_a = callnumber_a;
	}
	public String getCallnumber_first() {
		return callnumber_first;
	}
	public void setCallnumber_first(String callnumber_first) {
		this.callnumber_first = callnumber_first;
	}
	public String getCallnumber_first_code() {
		return callnumber_first_code;
	}
	public void setCallnumber_first_code(String callnumber_first_code) {
		this.callnumber_first_code = callnumber_first_code;
	}
	public String getCallnumber_subject() {
		return callnumber_subject;
	}
	public void setCallnumber_subject(String callnumber_subject) {
		this.callnumber_subject = callnumber_subject;
	}
	public String getCallnumber_subject_code() {
		return callnumber_subject_code;
	}
	public void setCallnumber_subject_code(String callnumber_subject_code) {
		this.callnumber_subject_code = callnumber_subject_code;
	}
	public String getCallnumber_label() {
		return callnumber_label;
	}
	public void setCallnumber_label(String callnumber_label) {
		this.callnumber_label = callnumber_label;
	}
	public java.util.LinkedHashSet<String> getDewey_hundreds() {
		return dewey_hundreds;
	}
	public void setDewey_hundreds(java.util.LinkedHashSet<String> dewey_hundreds) {
		this.dewey_hundreds = dewey_hundreds;
	}
	public java.util.LinkedHashSet<String> getDewey_tens() {
		return dewey_tens;
	}
	public void setDewey_tens(java.util.LinkedHashSet<String> dewey_tens) {
		this.dewey_tens = dewey_tens;
	}
	public java.util.LinkedHashSet<String> getDewey_ones() {
		return dewey_ones;
	}
	public void setDewey_ones(java.util.LinkedHashSet<String> dewey_ones) {
		this.dewey_ones = dewey_ones;
	}
	public java.util.LinkedHashSet<String> getDewey_full() {
		return dewey_full;
	}
	public void setDewey_full(java.util.LinkedHashSet<String> dewey_full) {
		this.dewey_full = dewey_full;
	}
	public String getDewey_sort() {
		return dewey_sort;
	}
	public void setDewey_sort(String dewey_sort) {
		this.dewey_sort = dewey_sort;
	}
	public String getDewey_raw() {
		return dewey_raw;
	}
	public void setDewey_raw(String dewey_raw) {
		this.dewey_raw = dewey_raw;
	}
	public java.util.LinkedHashSet<String> getAuthor2() {
		return author2;
	}
	public void setAuthor2(java.util.LinkedHashSet<String> author2) {
		this.author2 = author2;
	}
	public java.util.LinkedHashSet<String> getAuthor2Str() {
		return author2Str;
	}
	public void setAuthor2Str(java.util.LinkedHashSet<String> author2Str) {
		this.author2Str = author2Str;
	}
	public java.util.LinkedHashSet<String> getAuthor2_role() {
		return author2_role;
	}
	public void setAuthor2_role(java.util.LinkedHashSet<String> author2_role) {
		this.author2_role = author2_role;
	}
	public String getAuthor_fuller() {
		return author_fuller;
	}
	public void setAuthor_fuller(String author_fuller) {
		this.author_fuller = author_fuller;
	}
	public java.util.LinkedHashSet<String> getAuthor_additional() {
		return author_additional;
	}
	public void setAuthor_additional(
			java.util.LinkedHashSet<String> author_additional) {
		this.author_additional = author_additional;
	}
	public java.util.LinkedHashSet<String> getAuthor_additionalStr() {
		return author_additionalStr;
	}
	public void setAuthor_additionalStr(
			java.util.LinkedHashSet<String> author_additionalStr) {
		this.author_additionalStr = author_additionalStr;
	}
	public java.util.LinkedHashSet<String> getTitle_alt() {
		return title_alt;
	}
	public void setTitle_alt(java.util.LinkedHashSet<String> title_alt) {
		this.title_alt = title_alt;
	}
	public java.util.LinkedHashSet<String> getTitle_old() {
		return title_old;
	}
	public void setTitle_old(java.util.LinkedHashSet<String> title_old) {
		this.title_old = title_old;
	}
	public java.util.LinkedHashSet<String> getTitle_new() {
		return title_new;
	}
	public void setTitle_new(java.util.LinkedHashSet<String> title_new) {
		this.title_new = title_new;
	}
	public java.util.LinkedHashSet<String> getDateSpan() {
		return dateSpan;
	}
	public void setDateSpan(java.util.LinkedHashSet<String> dateSpan) {
		this.dateSpan = dateSpan;
	}
	public java.util.LinkedHashSet<String> getSeries() {
		return series;
	}
	public void setSeries(java.util.LinkedHashSet<String> series) {
		this.series = series;
	}
	public java.util.LinkedHashSet<String> getSeries2() {
		return series2;
	}
	public void setSeries2(java.util.LinkedHashSet<String> series2) {
		this.series2 = series2;
	}
	public java.util.LinkedHashSet<String> getTopic() {
		return topic;
	}
	public void setTopic(java.util.LinkedHashSet<String> topic) {
		this.topic = topic;
	}
	public java.util.LinkedHashSet<String> getTopic_unstemmed() {
		return topic_unstemmed;
	}
	public void setTopic_unstemmed(java.util.LinkedHashSet<String> topic_unstemmed) {
		this.topic_unstemmed = topic_unstemmed;
	}
	public java.util.LinkedHashSet<String> getTopic_facet() {
		return topic_facet;
	}
	public void setTopic_facet(java.util.LinkedHashSet<String> topic_facet) {
		this.topic_facet = topic_facet;
	}
	public java.util.LinkedHashSet<String> getTopic_browse() {
		return topic_browse;
	}
	public void setTopic_browse(java.util.LinkedHashSet<String> topic_browse) {
		this.topic_browse = topic_browse;
	}
	public java.util.LinkedHashSet<String> getAuthor_browse() {
		return author_browse;
	}
	public void setAuthor_browse(java.util.LinkedHashSet<String> author_browse) {
		this.author_browse = author_browse;
	}
	public java.util.LinkedHashSet<String> getGenre() {
		return genre;
	}
	public void setGenre(java.util.LinkedHashSet<String> genre) {
		this.genre = genre;
	}
	public java.util.LinkedHashSet<String> getGenre_facet() {
		return genre_facet;
	}
	public void setGenre_facet(java.util.LinkedHashSet<String> genre_facet) {
		this.genre_facet = genre_facet;
	}
	public java.util.LinkedHashSet<String> getGeographic() {
		return geographic;
	}
	public void setGeographic(java.util.LinkedHashSet<String> geographic) {
		this.geographic = geographic;
	}
	public java.util.LinkedHashSet<String> getGeographic_facet() {
		return geographic_facet;
	}
	public void setGeographic_facet(java.util.LinkedHashSet<String> geographic_facet) {
		this.geographic_facet = geographic_facet;
	}
	public java.util.LinkedHashSet<String> getEra() {
		return era;
	}
	public void setEra(java.util.LinkedHashSet<String> era) {
		this.era = era;
	}
	public java.util.LinkedHashSet<String> getEra_facet() {
		return era_facet;
	}
	public void setEra_facet(java.util.LinkedHashSet<String> era_facet) {
		this.era_facet = era_facet;
	}
	public String getIllustrated() {
		return illustrated;
	}
	public void setIllustrated(String illustrated) {
		this.illustrated = illustrated;
	}
	public String getLong_lat() {
		return long_lat;
	}
	public void setLong_lat(String long_lat) {
		this.long_lat = long_lat;
	}
	public String getContainer_title() {
		return container_title;
	}
	public void setContainer_title(String container_title) {
		this.container_title = container_title;
	}
	public String getContainer_volume() {
		return container_volume;
	}
	public void setContainer_volume(String container_volume) {
		this.container_volume = container_volume;
	}
	public String getContainer_issue() {
		return container_issue;
	}
	public void setContainer_issue(String container_issue) {
		this.container_issue = container_issue;
	}
	public String getContainer_start_page() {
		return container_start_page;
	}
	public void setContainer_start_page(String container_start_page) {
		this.container_start_page = container_start_page;
	}
	public String getContainer_reference() {
		return container_reference;
	}
	public void setContainer_reference(String container_reference) {
		this.container_reference = container_reference;
	}
	public String getRecordtype() {
		return recordtype;
	}
	public void setRecordtype(String recordtype) {
		this.recordtype = recordtype;
	}
	public Date getFirst_indexed() {
		return first_indexed;
	}
	public void setFirst_indexed(Date first_indexed) {
		this.first_indexed = first_indexed;
	}
	public Date getLast_indexed() {
		return last_indexed;
	}
	public void setLast_indexed(Date last_indexed) {
		this.last_indexed = last_indexed;
	}
	@Override
	public String toString() {
		return "VuFindMarc [id=" + id + ", fullrecord=" + fullrecord
				+ ", marc_error=" + marc_error + ", allfields=" + allfields
				+ ", allfields_unstemmed=" + allfields_unstemmed
				+ ", fulltext=" + fulltext + ", fulltext_unstemmed="
				+ fulltext_unstemmed + ", spelling=" + spelling
				+ ", spellingShingle=" + spellingShingle 
				+ ", text_cjk=" + cjk
				+ ", institution="
				+ institution + ", collection=" + collection + ", building="
				+ building + ", language=" + language + ", format=" + format
				+ ", author=" + author + ", author_letter=" + author_letter
				+ ", authorStr=" + authorStr + ", title=" + title
				+ ", title_sort=" + title_sort + ", title_sub=" + title_sub
				+ ", title_short=" + title_short + ", title_full=" + title_full
				+ ", title_full_unstemmed=" + title_full_unstemmed
				+ ", title_fullStr=" + title_fullStr + ", title_auth="
				+ title_auth + ", physical=" + physical + ", publisher="
				+ publisher + ", publisherStr=" + publisherStr
				+ ", publishDate=" + publishDate + ", publishDateSort="
				+ publishDateSort + ", edition=" + edition + ", description="
				+ description + ", contents=" + contents + ", url=" + url
				+ ", thumbnail=" + thumbnail + ", lccn=" + lccn + ", ctrlnum="
				+ ctrlnum + ", isbn=" + isbn + ", issn=" + issn + ", oclc_num="
				+ oclc_num + ", callnumber=" + callnumber + ", callnumber_a="
				+ callnumber_a + ", callnumber_first=" + callnumber_first
				+ ", callnumber_first_code=" + callnumber_first_code
				+ ", callnumber_subject=" + callnumber_subject
				+ ", callnumber_subject_code=" + callnumber_subject_code
				+ ", callnumber_label=" + callnumber_label
				+ ", dewey_hundreds=" + dewey_hundreds + ", dewey_tens="
				+ dewey_tens + ", dewey_ones=" + dewey_ones + ", dewey_full="
				+ dewey_full + ", dewey_sort=" + dewey_sort + ", dewey_raw="
				+ dewey_raw + ", author2=" + author2 + ", author2Str="
				+ author2Str + ", author2_role=" + author2_role
				+ ", author_fuller=" + author_fuller + ", author_additional="
				+ author_additional + ", author_additionalStr="
				+ author_additionalStr + ", title_alt=" + title_alt
				+ ", title_old=" + title_old + ", title_new=" + title_new
				+ ", dateSpan=" + dateSpan + ", series=" + series
				+ ", series2=" + series2 + ", topic=" + topic
				+ ", topic_unstemmed=" + topic_unstemmed + ", topic_facet="
				+ topic_facet + ", topic_browse=" + topic_browse
				+ ", author_browse=" + author_browse + ", genre=" + genre
				+ ", genre_facet=" + genre_facet + ", geographic=" + geographic
				+ ", geographic_facet=" + geographic_facet + ", era=" + era
				+ ", era_facet=" + era_facet + ", illustrated=" + illustrated
				+ ", long_lat=" + long_lat + ", container_title="
				+ container_title + ", container_volume=" + container_volume
				+ ", container_issue=" + container_issue
				+ ", container_start_page=" + container_start_page
				+ ", container_reference=" + container_reference
				+ ", recordtype=" + recordtype + ", first_indexed="
				+ first_indexed + ", last_indexed=" + last_indexed + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((allfields == null) ? 0 : allfields.hashCode());
		result = prime
				* result
				+ ((allfields_unstemmed == null) ? 0 : allfields_unstemmed
						.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((author2 == null) ? 0 : author2.hashCode());
		result = prime * result
				+ ((author2Str == null) ? 0 : author2Str.hashCode());
		result = prime * result
				+ ((author2_role == null) ? 0 : author2_role.hashCode());
		result = prime * result
				+ ((authorStr == null) ? 0 : authorStr.hashCode());
		result = prime
				* result
				+ ((author_additional == null) ? 0 : author_additional
						.hashCode());
		result = prime
				* result
				+ ((author_additionalStr == null) ? 0 : author_additionalStr
						.hashCode());
		result = prime * result
				+ ((author_browse == null) ? 0 : author_browse.hashCode());
		result = prime * result
				+ ((author_fuller == null) ? 0 : author_fuller.hashCode());
		result = prime * result
				+ ((author_letter == null) ? 0 : author_letter.hashCode());
		result = prime * result
				+ ((building == null) ? 0 : building.hashCode());
		result = prime * result
				+ ((callnumber == null) ? 0 : callnumber.hashCode());
		result = prime * result
				+ ((callnumber_a == null) ? 0 : callnumber_a.hashCode());
		result = prime
				* result
				+ ((callnumber_first == null) ? 0 : callnumber_first.hashCode());
		result = prime
				* result
				+ ((callnumber_first_code == null) ? 0 : callnumber_first_code
						.hashCode());
		result = prime
				* result
				+ ((callnumber_label == null) ? 0 : callnumber_label.hashCode());
		result = prime
				* result
				+ ((callnumber_subject == null) ? 0 : callnumber_subject
						.hashCode());
		result = prime
				* result
				+ ((callnumber_subject_code == null) ? 0
						: callnumber_subject_code.hashCode());
		result = prime * result
				+ ((collection == null) ? 0 : collection.hashCode());
		result = prime * result
				+ ((container_issue == null) ? 0 : container_issue.hashCode());
		result = prime
				* result
				+ ((container_reference == null) ? 0 : container_reference
						.hashCode());
		result = prime
				* result
				+ ((container_start_page == null) ? 0 : container_start_page
						.hashCode());
		result = prime * result
				+ ((container_title == null) ? 0 : container_title.hashCode());
		result = prime
				* result
				+ ((container_volume == null) ? 0 : container_volume.hashCode());
		result = prime * result
				+ ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((ctrlnum == null) ? 0 : ctrlnum.hashCode());
		result = prime * result
				+ ((dateSpan == null) ? 0 : dateSpan.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((dewey_full == null) ? 0 : dewey_full.hashCode());
		result = prime * result
				+ ((dewey_hundreds == null) ? 0 : dewey_hundreds.hashCode());
		result = prime * result
				+ ((dewey_ones == null) ? 0 : dewey_ones.hashCode());
		result = prime * result
				+ ((dewey_raw == null) ? 0 : dewey_raw.hashCode());
		result = prime * result
				+ ((dewey_sort == null) ? 0 : dewey_sort.hashCode());
		result = prime * result
				+ ((dewey_tens == null) ? 0 : dewey_tens.hashCode());
		result = prime * result + ((edition == null) ? 0 : edition.hashCode());
		result = prime * result + ((era == null) ? 0 : era.hashCode());
		result = prime * result
				+ ((era_facet == null) ? 0 : era_facet.hashCode());
		result = prime * result
				+ ((first_indexed == null) ? 0 : first_indexed.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result
				+ ((fullrecord == null) ? 0 : fullrecord.hashCode());
		result = prime * result
				+ ((fulltext == null) ? 0 : fulltext.hashCode());
		result = prime
				* result
				+ ((fulltext_unstemmed == null) ? 0 : fulltext_unstemmed
						.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ ((genre_facet == null) ? 0 : genre_facet.hashCode());
		result = prime * result
				+ ((geographic == null) ? 0 : geographic.hashCode());
		result = prime
				* result
				+ ((geographic_facet == null) ? 0 : geographic_facet.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((illustrated == null) ? 0 : illustrated.hashCode());
		result = prime * result
				+ ((institution == null) ? 0 : institution.hashCode());
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + ((issn == null) ? 0 : issn.hashCode());
		result = prime * result
				+ ((language == null) ? 0 : language.hashCode());
		result = prime * result
				+ ((last_indexed == null) ? 0 : last_indexed.hashCode());
		result = prime * result + ((lccn == null) ? 0 : lccn.hashCode());
		result = prime * result
				+ ((long_lat == null) ? 0 : long_lat.hashCode());
		result = prime * result
				+ ((marc_error == null) ? 0 : marc_error.hashCode());
		result = prime * result
				+ ((oclc_num == null) ? 0 : oclc_num.hashCode());
		result = prime * result
				+ ((physical == null) ? 0 : physical.hashCode());
		result = prime * result
				+ ((publishDate == null) ? 0 : publishDate.hashCode());
		result = prime * result
				+ ((publishDateSort == null) ? 0 : publishDateSort.hashCode());
		result = prime * result
				+ ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result
				+ ((publisherStr == null) ? 0 : publisherStr.hashCode());
		result = prime * result
				+ ((recordtype == null) ? 0 : recordtype.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		result = prime * result + ((series2 == null) ? 0 : series2.hashCode());
		result = prime * result
				+ ((spelling == null) ? 0 : spelling.hashCode());
		result = prime * result
				+ ((spellingShingle == null) ? 0 : spellingShingle.hashCode());
		result = prime * result
				+ ((cjk == null) ? 0 : cjk.hashCode());
		result = prime * result
				+ ((thumbnail == null) ? 0 : thumbnail.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result
				+ ((title_alt == null) ? 0 : title_alt.hashCode());
		result = prime * result
				+ ((title_auth == null) ? 0 : title_auth.hashCode());
		result = prime * result
				+ ((title_full == null) ? 0 : title_full.hashCode());
		result = prime * result
				+ ((title_fullStr == null) ? 0 : title_fullStr.hashCode());
		result = prime
				* result
				+ ((title_full_unstemmed == null) ? 0 : title_full_unstemmed
						.hashCode());
		result = prime * result
				+ ((title_new == null) ? 0 : title_new.hashCode());
		result = prime * result
				+ ((title_old == null) ? 0 : title_old.hashCode());
		result = prime * result
				+ ((title_short == null) ? 0 : title_short.hashCode());
		result = prime * result
				+ ((title_sort == null) ? 0 : title_sort.hashCode());
		result = prime * result
				+ ((title_sub == null) ? 0 : title_sub.hashCode());
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result
				+ ((topic_browse == null) ? 0 : topic_browse.hashCode());
		result = prime * result
				+ ((topic_facet == null) ? 0 : topic_facet.hashCode());
		result = prime * result
				+ ((topic_unstemmed == null) ? 0 : topic_unstemmed.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		VuFindMarc other = (VuFindMarc) obj;
		if (allfields == null) {
			if (other.allfields != null)
				return false;
		} else if (!allfields.equals(other.allfields))
			return false;
		if (allfields_unstemmed == null) {
			if (other.allfields_unstemmed != null)
				return false;
		} else if (!allfields_unstemmed.equals(other.allfields_unstemmed))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (author2 == null) {
			if (other.author2 != null)
				return false;
		} else if (!author2.equals(other.author2))
			return false;
		if (author2Str == null) {
			if (other.author2Str != null)
				return false;
		} else if (!author2Str.equals(other.author2Str))
			return false;
		if (author2_role == null) {
			if (other.author2_role != null)
				return false;
		} else if (!author2_role.equals(other.author2_role))
			return false;
		if (authorStr == null) {
			if (other.authorStr != null)
				return false;
		} else if (!authorStr.equals(other.authorStr))
			return false;
		if (author_additional == null) {
			if (other.author_additional != null)
				return false;
		} else if (!author_additional.equals(other.author_additional))
			return false;
		if (author_additionalStr == null) {
			if (other.author_additionalStr != null)
				return false;
		} else if (!author_additionalStr.equals(other.author_additionalStr))
			return false;
		if (author_browse == null) {
			if (other.author_browse != null)
				return false;
		} else if (!author_browse.equals(other.author_browse))
			return false;
		if (author_fuller == null) {
			if (other.author_fuller != null)
				return false;
		} else if (!author_fuller.equals(other.author_fuller))
			return false;
		if (author_letter == null) {
			if (other.author_letter != null)
				return false;
		} else if (!author_letter.equals(other.author_letter))
			return false;
		if (building == null) {
			if (other.building != null)
				return false;
		} else if (!building.equals(other.building))
			return false;
		if (callnumber == null) {
			if (other.callnumber != null)
				return false;
		} else if (!callnumber.equals(other.callnumber))
			return false;
		if (callnumber_a == null) {
			if (other.callnumber_a != null)
				return false;
		} else if (!callnumber_a.equals(other.callnumber_a))
			return false;
		if (callnumber_first == null) {
			if (other.callnumber_first != null)
				return false;
		} else if (!callnumber_first.equals(other.callnumber_first))
			return false;
		if (callnumber_first_code == null) {
			if (other.callnumber_first_code != null)
				return false;
		} else if (!callnumber_first_code.equals(other.callnumber_first_code))
			return false;
		if (callnumber_label == null) {
			if (other.callnumber_label != null)
				return false;
		} else if (!callnumber_label.equals(other.callnumber_label))
			return false;
		if (callnumber_subject == null) {
			if (other.callnumber_subject != null)
				return false;
		} else if (!callnumber_subject.equals(other.callnumber_subject))
			return false;
		if (callnumber_subject_code == null) {
			if (other.callnumber_subject_code != null)
				return false;
		} else if (!callnumber_subject_code
				.equals(other.callnumber_subject_code))
			return false;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (container_issue == null) {
			if (other.container_issue != null)
				return false;
		} else if (!container_issue.equals(other.container_issue))
			return false;
		if (container_reference == null) {
			if (other.container_reference != null)
				return false;
		} else if (!container_reference.equals(other.container_reference))
			return false;
		if (container_start_page == null) {
			if (other.container_start_page != null)
				return false;
		} else if (!container_start_page.equals(other.container_start_page))
			return false;
		if (container_title == null) {
			if (other.container_title != null)
				return false;
		} else if (!container_title.equals(other.container_title))
			return false;
		if (container_volume == null) {
			if (other.container_volume != null)
				return false;
		} else if (!container_volume.equals(other.container_volume))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (ctrlnum == null) {
			if (other.ctrlnum != null)
				return false;
		} else if (!ctrlnum.equals(other.ctrlnum))
			return false;
		if (dateSpan == null) {
			if (other.dateSpan != null)
				return false;
		} else if (!dateSpan.equals(other.dateSpan))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dewey_full == null) {
			if (other.dewey_full != null)
				return false;
		} else if (!dewey_full.equals(other.dewey_full))
			return false;
		if (dewey_hundreds == null) {
			if (other.dewey_hundreds != null)
				return false;
		} else if (!dewey_hundreds.equals(other.dewey_hundreds))
			return false;
		if (dewey_ones == null) {
			if (other.dewey_ones != null)
				return false;
		} else if (!dewey_ones.equals(other.dewey_ones))
			return false;
		if (dewey_raw == null) {
			if (other.dewey_raw != null)
				return false;
		} else if (!dewey_raw.equals(other.dewey_raw))
			return false;
		if (dewey_sort == null) {
			if (other.dewey_sort != null)
				return false;
		} else if (!dewey_sort.equals(other.dewey_sort))
			return false;
		if (dewey_tens == null) {
			if (other.dewey_tens != null)
				return false;
		} else if (!dewey_tens.equals(other.dewey_tens))
			return false;
		if (edition == null) {
			if (other.edition != null)
				return false;
		} else if (!edition.equals(other.edition))
			return false;
		if (era == null) {
			if (other.era != null)
				return false;
		} else if (!era.equals(other.era))
			return false;
		if (era_facet == null) {
			if (other.era_facet != null)
				return false;
		} else if (!era_facet.equals(other.era_facet))
			return false;
		if (first_indexed == null) {
			if (other.first_indexed != null)
				return false;
		} else if (!first_indexed.equals(other.first_indexed))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (fullrecord == null) {
			if (other.fullrecord != null)
				return false;
		} else if (!fullrecord.equals(other.fullrecord))
			return false;
		if (fulltext == null) {
			if (other.fulltext != null)
				return false;
		} else if (!fulltext.equals(other.fulltext))
			return false;
		if (fulltext_unstemmed == null) {
			if (other.fulltext_unstemmed != null)
				return false;
		} else if (!fulltext_unstemmed.equals(other.fulltext_unstemmed))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (genre_facet == null) {
			if (other.genre_facet != null)
				return false;
		} else if (!genre_facet.equals(other.genre_facet))
			return false;
		if (geographic == null) {
			if (other.geographic != null)
				return false;
		} else if (!geographic.equals(other.geographic))
			return false;
		if (geographic_facet == null) {
			if (other.geographic_facet != null)
				return false;
		} else if (!geographic_facet.equals(other.geographic_facet))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (illustrated == null) {
			if (other.illustrated != null)
				return false;
		} else if (!illustrated.equals(other.illustrated))
			return false;
		if (institution == null) {
			if (other.institution != null)
				return false;
		} else if (!institution.equals(other.institution))
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (issn == null) {
			if (other.issn != null)
				return false;
		} else if (!issn.equals(other.issn))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (last_indexed == null) {
			if (other.last_indexed != null)
				return false;
		} else if (!last_indexed.equals(other.last_indexed))
			return false;
		if (lccn == null) {
			if (other.lccn != null)
				return false;
		} else if (!lccn.equals(other.lccn))
			return false;
		if (long_lat == null) {
			if (other.long_lat != null)
				return false;
		} else if (!long_lat.equals(other.long_lat))
			return false;
		if (marc_error == null) {
			if (other.marc_error != null)
				return false;
		} else if (!marc_error.equals(other.marc_error))
			return false;
		if (oclc_num == null) {
			if (other.oclc_num != null)
				return false;
		} else if (!oclc_num.equals(other.oclc_num))
			return false;
		if (physical == null) {
			if (other.physical != null)
				return false;
		} else if (!physical.equals(other.physical))
			return false;
		if (publishDate == null) {
			if (other.publishDate != null)
				return false;
		} else if (!publishDate.equals(other.publishDate))
			return false;
		if (publishDateSort == null) {
			if (other.publishDateSort != null)
				return false;
		} else if (!publishDateSort.equals(other.publishDateSort))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (publisherStr == null) {
			if (other.publisherStr != null)
				return false;
		} else if (!publisherStr.equals(other.publisherStr))
			return false;
		if (recordtype == null) {
			if (other.recordtype != null)
				return false;
		} else if (!recordtype.equals(other.recordtype))
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
		} else if (!series.equals(other.series))
			return false;
		if (series2 == null) {
			if (other.series2 != null)
				return false;
		} else if (!series2.equals(other.series2))
			return false;
		if (spelling == null) {
			if (other.spelling != null)
				return false;
		} else if (!spelling.equals(other.spelling))
			return false;
		if (spellingShingle == null) {
			if (other.spellingShingle != null)
				return false;
		} else if (!spellingShingle.equals(other.spellingShingle))
			return false;
		if (cjk == null) {
			if (other.cjk != null)
				return false;
		} else if (!cjk.equals(other.cjk))
			return false;
		if (thumbnail == null) {
			if (other.thumbnail != null)
				return false;
		} else if (!thumbnail.equals(other.thumbnail))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (title_alt == null) {
			if (other.title_alt != null)
				return false;
		} else if (!title_alt.equals(other.title_alt))
			return false;
		if (title_auth == null) {
			if (other.title_auth != null)
				return false;
		} else if (!title_auth.equals(other.title_auth))
			return false;
		if (title_full == null) {
			if (other.title_full != null)
				return false;
		} else if (!title_full.equals(other.title_full))
			return false;
		if (title_fullStr == null) {
			if (other.title_fullStr != null)
				return false;
		} else if (!title_fullStr.equals(other.title_fullStr))
			return false;
		if (title_full_unstemmed == null) {
			if (other.title_full_unstemmed != null)
				return false;
		} else if (!title_full_unstemmed.equals(other.title_full_unstemmed))
			return false;
		if (title_new == null) {
			if (other.title_new != null)
				return false;
		} else if (!title_new.equals(other.title_new))
			return false;
		if (title_old == null) {
			if (other.title_old != null)
				return false;
		} else if (!title_old.equals(other.title_old))
			return false;
		if (title_short == null) {
			if (other.title_short != null)
				return false;
		} else if (!title_short.equals(other.title_short))
			return false;
		if (title_sort == null) {
			if (other.title_sort != null)
				return false;
		} else if (!title_sort.equals(other.title_sort))
			return false;
		if (title_sub == null) {
			if (other.title_sub != null)
				return false;
		} else if (!title_sub.equals(other.title_sub))
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (topic_browse == null) {
			if (other.topic_browse != null)
				return false;
		} else if (!topic_browse.equals(other.topic_browse))
			return false;
		if (topic_facet == null) {
			if (other.topic_facet != null)
				return false;
		} else if (!topic_facet.equals(other.topic_facet))
			return false;
		if (topic_unstemmed == null) {
			if (other.topic_unstemmed != null)
				return false;
		} else if (!topic_unstemmed.equals(other.topic_unstemmed))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	

}
