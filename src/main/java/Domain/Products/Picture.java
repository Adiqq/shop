package Domain.Products;

public class Picture {
    private byte[] content;
    private PictureType pictureType;

    public byte[] getContent() {
        return content;
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
}
