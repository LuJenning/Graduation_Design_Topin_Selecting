package com.gdts.core.pagination;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author <a href=mailto:amu_1115@126.com> amu </a>
 * @version $ Result.java 2015-7-12 02:30:18
 */
public class Result<T> implements Serializable {

	
	private int offset;
    private int size;
    private int total;
   // private int nextPageNumber;
    

    private List<T> data;
    @Override
	public String toString() {
		return "Result [offset=" + offset + ", size=" + size + ", total=" + total + ", data=" + data + "]";
	}

	public boolean getisFirst() {
    	return offset == 0;
    }
    
    public boolean getisLast() {
    	return !gethasNext();
    }
    
    public boolean gethasNext() {
    	
    	if (data == null || size < 0) {
    		return false;
    	}
    	//System.out.println("data.size()="+data.size());
    	//System.out.println("size="+size);
    	return data.size() >= size;
    }
    
   
    
    public int getTotalPage() {
    	if (size < 0) return 1;
    	
    	return total % size == 0 ? total/size : total/size + 1;
    }
    
    public int getPage() {
    	return offset/size + 1;
    }
    
    public int getnextPageNumber(){
    	return getPage()+1;
    }
    
    public int getpreviousPageNumber(){
    	return getPage()-1;
    }
    public Result() {
    }
    
    public Result(int offset, int size) {
    	this(null, offset, size);
    }
    
    public Result(List<T> data, int offset, int size) {
    	this.data = data;
    	this.offset = offset;
    	this.size = size;
    }

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
