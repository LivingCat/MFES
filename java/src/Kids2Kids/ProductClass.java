package Kids2Kids;

import java.util.*;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProductClass {
  private String name;

  public void cg_init_ProductClass_1(final String n) {

    name = n;
    return;
  }

  public ProductClass(final String n) {

    cg_init_ProductClass_1(n);
  }

  public String getName() {

    return name;
  }

  public ProductClass() {}

  public String toString() {

    return name;
  }
  
  

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductClass other = (ProductClass) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
