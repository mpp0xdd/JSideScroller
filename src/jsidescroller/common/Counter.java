package jsidescroller.common;

abstract class Counter<T extends Number> {

  protected T count;

  public Counter() {
    this.count = defaultValue();
  }

  @Override
  public String toString() {
    return count.toString();
  }

  public final void reset() {
    this.count = defaultValue();
  }

  public final T getCount() {
    return count;
  }

  public final void increment() {
    postIncrement();
  }

  public final void decrement() {
    postDecrement();
  }

  public abstract T preIncrement();

  public abstract T postIncrement();

  public abstract T preDecrement();

  public abstract T postDecrement();

  protected abstract T defaultValue();

  protected abstract T minimumValue();

  protected abstract T maximumValue();
}
