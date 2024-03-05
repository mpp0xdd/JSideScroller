package jsidescroller.common.counters;

public interface Counter<T extends Number, U extends Number> {

  U getCount();

  boolean isCounterStop();

  void reset();

  void increment();

  U incrementAndGet();

  U getAndIncrement();

  void decrement();

  U decrementAndGet();

  U getAndDecrement();

  void add(U value);

  U addAndGet(U value);

  U getAndAdd(U value);

  Number wrapAround(U value);

  void incrementExact() throws CounterException;

  U incrementExactAndGet() throws CounterException;

  U getAndIncrementExact() throws CounterException;

  void decrementExact() throws CounterException;

  U decrementExactAndGet() throws CounterException;

  U getAndDecrementExact() throws CounterException;

  void addExact(U value) throws CounterException;

  U addExactAndGet(U value) throws CounterException;

  U getAndAddExact(U value) throws CounterException;

  U minimumValue();

  U maximumValue();

  U capacity();

  @Override
  String toString();
}
