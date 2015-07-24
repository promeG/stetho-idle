package com.facebook.stetho.dumpapp;


public interface DumperPlugin {

  String getName();

  void dump(DumperContext dumpContext) throws DumpException;
}
