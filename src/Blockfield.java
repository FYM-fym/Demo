public class Blockfield {
//    块的一些属性，与type相对应
    public BlockType blockType;
    public int number;
    public int width;
    public int height;
    public Blockfield(BlockType blockType,int number){
        this.blockType = blockType;
        switch (blockType) {
            case SQUARE:
                width = 2;
                height = 2;
                this.number=number;
                break;
            case HORIZONTAL:
                width = 2;
                height = 1;
                this.number=number;
                break;
            case VERTICAL:
                width = 1;
                height = 2;
                this.number=number;
                break;
            case SINGLE:
            case BLANK:
                width = 1;
                height = 1;
                this.number=number;
                break;

        }
    }
}
