package me.thehutch.cubiverse.data;

import org.spout.api.component.impl.DatatableComponent;
/**
 * @author thehutch
 */
public interface Storable {

	public void save(DatatableComponent datatable);

	public void load(DatatableComponent datatable);

}