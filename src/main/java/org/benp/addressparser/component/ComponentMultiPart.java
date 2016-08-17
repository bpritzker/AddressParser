package org.benp.addressparser.component;


/**
 * Multi parts just mean they have can have more than one part and have the concept of complete. 
 * A base component does not have the Complete/Partial concept.
 * @author Ben P
 *
 */
public abstract class ComponentMultiPart extends ComponentBase {
	
	public abstract boolean isComplete();
	
	public abstract boolean isPartial();
	
}
