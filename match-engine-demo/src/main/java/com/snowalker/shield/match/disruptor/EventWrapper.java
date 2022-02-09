package com.snowalker.shield.match.disruptor;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/2/2 22:44
 * @className
 * @desc
 */
public class EventWrapper<T> {
    T t;
    long id;
    boolean isAHandled;
    boolean isBHandled;


    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public static class EventWrapperBuilder<T> {
        @SuppressWarnings("all")
        private T t;
        @SuppressWarnings("all")
        private long id;
        @SuppressWarnings("all")
        private boolean isAHandled;
        @SuppressWarnings("all")
        private boolean isBHandled;

        @SuppressWarnings("all")
        EventWrapperBuilder() {
        }

        @SuppressWarnings("all")
        public EventWrapper.EventWrapperBuilder<T> t(final T t) {
            this.t = t;
            return this;
        }

        @SuppressWarnings("all")
        public EventWrapper.EventWrapperBuilder<T> id(final long id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        public EventWrapper.EventWrapperBuilder<T> isAHandled(final boolean isAHandled) {
            this.isAHandled = isAHandled;
            return this;
        }

        @SuppressWarnings("all")
        public EventWrapper.EventWrapperBuilder<T> isBHandled(final boolean isBHandled) {
            this.isBHandled = isBHandled;
            return this;
        }

        @SuppressWarnings("all")
        public EventWrapper<T> build() {
            return new EventWrapper<T>(this.t, this.id, this.isAHandled, this.isBHandled);
        }

        @Override
        @SuppressWarnings("all")
        public String toString() {
            return "EventWrapper.EventWrapperBuilder(t=" + this.t + ", id=" + this.id + ", isAHandled=" + this.isAHandled + ", isBHandled=" + this.isBHandled + ")";
        }
    }

    @SuppressWarnings("all")
    public static <T> EventWrapper.EventWrapperBuilder<T> builder() {
        return new EventWrapper.EventWrapperBuilder<T>();
    }

    @SuppressWarnings("all")
    public T getT() {
        return this.t;
    }

    @SuppressWarnings("all")
    public long getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    public boolean isAHandled() {
        return this.isAHandled;
    }

    @SuppressWarnings("all")
    public boolean isBHandled() {
        return this.isBHandled;
    }

    @SuppressWarnings("all")
    public void setT(final T t) {
        this.t = t;
    }

    @SuppressWarnings("all")
    public void setId(final long id) {
        this.id = id;
    }

    @SuppressWarnings("all")
    public void setAHandled(final boolean isAHandled) {
        this.isAHandled = isAHandled;
    }

    @SuppressWarnings("all")
    public void setBHandled(final boolean isBHandled) {
        this.isBHandled = isBHandled;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof EventWrapper)) return false;
        final EventWrapper<?> other = (EventWrapper<?>) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.isAHandled() != other.isAHandled()) return false;
        if (this.isBHandled() != other.isBHandled()) return false;
        final Object this$t = this.getT();
        final Object other$t = other.getT();
        if (this$t == null ? other$t != null : !this$t.equals(other$t)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof EventWrapper;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $id = this.getId();
        result = result * PRIME + (int) ($id >>> 32 ^ $id);
        result = result * PRIME + (this.isAHandled() ? 79 : 97);
        result = result * PRIME + (this.isBHandled() ? 79 : 97);
        final Object $t = this.getT();
        result = result * PRIME + ($t == null ? 43 : $t.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "EventWrapper(t=" + this.getT() + ", id=" + this.getId() + ", isAHandled=" + this.isAHandled() + ", isBHandled=" + this.isBHandled() + ")";
    }

    @SuppressWarnings("all")
    public EventWrapper(final T t, final long id, final boolean isAHandled, final boolean isBHandled) {
        this.t = t;
        this.id = id;
        this.isAHandled = isAHandled;
        this.isBHandled = isBHandled;
    }

    @SuppressWarnings("all")
    public EventWrapper() {
    }
    //</editor-fold>
}
