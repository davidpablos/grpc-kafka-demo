package com.dpablos.grpckafkademo.user.builder;

public abstract class Builder<T> {

	public abstract T build(int index);

	public T build() {
		return this.build(1);
	}

}
