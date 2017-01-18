package Domain.Products;

import java.util.Base64;

/**
 * Value type representing picture
 */
public class Picture {
    private byte[] content;
    private PictureType pictureType;

    /**
     * Get picture encoded as base-64
     * @return base-64 encoded string
     */
    public String getContent() {
        if(content != null) {
            return Base64.getEncoder().encodeToString(content);
        }
        return null;
    }
    public byte[] getContentAsByteArray() {
        return content;
    }

    /**
     * Set picture with base-64 encoded string
     * @param content base-64 encoded string
     */
    public void setContent(String content) {
        if(content != null) {
            this.content = Base64.getDecoder().decode(content);
        }
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public PictureType getPictureType() {
        return pictureType;
    }

    public void setPictureType(PictureType pictureType) {
        this.pictureType = pictureType;
    }

    /**
     * Image extension
     * @param extension jpg,png
     */
    public void setPictureType(String extension){
        switch (extension){
            case "jpg":
            case "jpeg":
                setPictureType(PictureType.JPEG);
                break;
            case "png":
                setPictureType(PictureType.PNG);
                break;
            default:
                throw new IllegalArgumentException("Not valid picture type");

        }
    }
}
