package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cafe24.lms.domain.DVD;

@Entity
public class DVDCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dvdcategory_no")
	private Long no;
	private String name;

	@OneToMany(mappedBy = "dvdCategory")
	private List<DVD> dvds = new ArrayList<DVD>();

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DVD> getDvds() {
		return dvds;
	}

	public void setDvds(List<DVD> dvds) {
		this.dvds = dvds;
	}

	@Override
	public String toString() {
		return "DVDCategory [no=" + no + ", name=" + name + "]";
	}

}
