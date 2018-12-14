package com.dy.sensor.common.support.xls;


public class FileBean {

    private String name;

    private String contenttype;

    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
    * 
    */
    public FileBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileBean [name=" + name + ", contenttype=" + contenttype + ", content=" + content + "]";
    }

}
