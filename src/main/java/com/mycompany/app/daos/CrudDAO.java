package com.mycompany.app.daos;

import java.util.List;

public interface CrudDAO<T> {
  /**
   * Saves the object to the data source.
   *
   * @param obj the object to be saved
   */
  void save(T obj);

  /**
   * Updates the object with the specified ID in the data source.
   *
   * @param id the ID of the object to be updated
   */
  void update(String id);

  /**
   * Deletes the object with the specified ID from the data source.
   *
   * @param id the ID of the object to be deleted
   */
  void delete(String id);

  /**
   * Finds and retrieves the object with the specified ID from the data source.
   *
   * @param id the ID of the object to find
   * @return the found object, or null if not found
   */
  T findById(String id);

  /**
   * Retrieves all objects from the data source.
   *
   * @return a list of all objects
   */
  List<T> findAll();
}
