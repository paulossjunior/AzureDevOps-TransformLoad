package br.nemo.immigrant.ontology.transformaload.msdevops.sro.util;

public interface Mapper <T> {
  public T map (String element) throws Exception;
}
