package com.dpablos.grpckafkademo.user.contract;

public interface Mapper<T, V> {

	V map(T input);

}