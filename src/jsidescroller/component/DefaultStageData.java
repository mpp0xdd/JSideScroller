package jsidescroller.component;

enum DefaultStageData {
  VOID('0'),
  BLOCK('1'),
  COIN('2'),
  ITEM_BLOCK('3'),
  ENEMY('4'),
  ;

  public static DefaultStageData of(final char data) {
    for (DefaultStageData stageData : values()) {
      if (data == stageData.data) {
        return stageData;
      }
    }
    throw new IllegalArgumentException(String.valueOf(data));
  }

  private final char data;

  private DefaultStageData(char data) {
    this.data = data;
  }
}
