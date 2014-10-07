package my.com.myriadeas.integral.data.jpa.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * The persistent class for the FAMAST database table.
 * 
 */
@Entity
public class Famast implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "fa01id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String fa01fund;

	private String fa01aktiviti;

	private BigDecimal fa01allo;

	private BigDecimal fa01comm;

	private String fa01cp;

	private Date fa01daterec;

	private BigDecimal fa01decr;

	private String fa01desc;

	private BigDecimal fa01encr;

	private BigDecimal fa01incr;

	private String fa01jbt;

	private BigDecimal fa01liab;

	private String fa01mak1;

	private String fa01mak2;

	private String fa01module;

	private String fa01objek;

	private BigDecimal fa01pay;

	private String fa01pk;

	private String fa01ptj;

	private String fa01recby;

	private Integer fa01seqc;

	private String fa01vote;

	public Famast() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFa01fund() {
		return this.fa01fund;
	}

	public void setFa01fund(String fa01fund) {
		this.fa01fund = fa01fund;
	}

	public String getFa01aktiviti() {
		return this.fa01aktiviti;
	}

	public void setFa01aktiviti(String fa01aktiviti) {
		this.fa01aktiviti = fa01aktiviti;
	}

	public BigDecimal getFa01allo() {
		return this.fa01allo;
	}

	public void setFa01allo(BigDecimal fa01allo) {
		this.fa01allo = fa01allo;
	}

	public BigDecimal getFa01comm() {
		return this.fa01comm;
	}

	public void setFa01comm(BigDecimal fa01comm) {
		this.fa01comm = fa01comm;
	}

	public String getFa01cp() {
		return this.fa01cp;
	}

	public void setFa01cp(String fa01cp) {
		this.fa01cp = fa01cp;
	}

	public Date getFa01daterec() {
		return this.fa01daterec;
	}

	public void setFa01daterec(Date fa01daterec) {
		this.fa01daterec = fa01daterec;
	}

	public BigDecimal getFa01decr() {
		return this.fa01decr;
	}

	public void setFa01decr(BigDecimal fa01decr) {
		this.fa01decr = fa01decr;
	}

	public String getFa01desc() {
		return this.fa01desc;
	}

	public void setFa01desc(String fa01desc) {
		this.fa01desc = fa01desc;
	}

	public BigDecimal getFa01encr() {
		return this.fa01encr;
	}

	public void setFa01encr(BigDecimal fa01encr) {
		this.fa01encr = fa01encr;
	}

	public BigDecimal getFa01incr() {
		return this.fa01incr;
	}

	public void setFa01incr(BigDecimal fa01incr) {
		this.fa01incr = fa01incr;
	}

	public String getFa01jbt() {
		return this.fa01jbt;
	}

	public void setFa01jbt(String fa01jbt) {
		this.fa01jbt = fa01jbt;
	}

	public BigDecimal getFa01liab() {
		return this.fa01liab;
	}

	public void setFa01liab(BigDecimal fa01liab) {
		this.fa01liab = fa01liab;
	}

	public String getFa01mak1() {
		return this.fa01mak1;
	}

	public void setFa01mak1(String fa01mak1) {
		this.fa01mak1 = fa01mak1;
	}

	public String getFa01mak2() {
		return this.fa01mak2;
	}

	public void setFa01mak2(String fa01mak2) {
		this.fa01mak2 = fa01mak2;
	}

	public String getFa01module() {
		return this.fa01module;
	}

	public void setFa01module(String fa01module) {
		this.fa01module = fa01module;
	}

	public String getFa01objek() {
		return this.fa01objek;
	}

	public void setFa01objek(String fa01objek) {
		this.fa01objek = fa01objek;
	}

	public BigDecimal getFa01pay() {
		return this.fa01pay;
	}

	public void setFa01pay(BigDecimal fa01pay) {
		this.fa01pay = fa01pay;
	}

	public String getFa01pk() {
		return this.fa01pk;
	}

	public void setFa01pk(String fa01pk) {
		this.fa01pk = fa01pk;
	}

	public String getFa01ptj() {
		return this.fa01ptj;
	}

	public void setFa01ptj(String fa01ptj) {
		this.fa01ptj = fa01ptj;
	}

	public String getFa01recby() {
		return this.fa01recby;
	}

	public void setFa01recby(String fa01recby) {
		this.fa01recby = fa01recby;
	}

	public Integer getFa01seqc() {
		return this.fa01seqc;
	}

	public void setFa01seqc(Integer fa01seqc) {
		this.fa01seqc = fa01seqc;
	}

	public String getFa01vote() {
		return this.fa01vote;
	}

	public void setFa01vote(String fa01vote) {
		this.fa01vote = fa01vote;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fa01aktiviti == null) ? 0 : fa01aktiviti.hashCode());
		result = prime * result
				+ ((fa01allo == null) ? 0 : fa01allo.hashCode());
		result = prime * result
				+ ((fa01comm == null) ? 0 : fa01comm.hashCode());
		result = prime * result + ((fa01cp == null) ? 0 : fa01cp.hashCode());
		result = prime * result
				+ ((fa01daterec == null) ? 0 : fa01daterec.hashCode());
		result = prime * result
				+ ((fa01decr == null) ? 0 : fa01decr.hashCode());
		result = prime * result
				+ ((fa01desc == null) ? 0 : fa01desc.hashCode());
		result = prime * result
				+ ((fa01encr == null) ? 0 : fa01encr.hashCode());
		result = prime * result
				+ ((fa01fund == null) ? 0 : fa01fund.hashCode());
		result = prime * result
				+ ((fa01incr == null) ? 0 : fa01incr.hashCode());
		result = prime * result + ((fa01jbt == null) ? 0 : fa01jbt.hashCode());
		result = prime * result
				+ ((fa01liab == null) ? 0 : fa01liab.hashCode());
		result = prime * result
				+ ((fa01mak1 == null) ? 0 : fa01mak1.hashCode());
		result = prime * result
				+ ((fa01mak2 == null) ? 0 : fa01mak2.hashCode());
		result = prime * result
				+ ((fa01module == null) ? 0 : fa01module.hashCode());
		result = prime * result
				+ ((fa01objek == null) ? 0 : fa01objek.hashCode());
		result = prime * result + ((fa01pay == null) ? 0 : fa01pay.hashCode());
		result = prime * result + ((fa01pk == null) ? 0 : fa01pk.hashCode());
		result = prime * result + ((fa01ptj == null) ? 0 : fa01ptj.hashCode());
		result = prime * result
				+ ((fa01recby == null) ? 0 : fa01recby.hashCode());
		result = prime * result
				+ ((fa01seqc == null) ? 0 : fa01seqc.hashCode());
		result = prime * result
				+ ((fa01vote == null) ? 0 : fa01vote.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Famast other = (Famast) obj;
		if (fa01aktiviti == null) {
			if (other.fa01aktiviti != null)
				return false;
		} else if (!fa01aktiviti.equals(other.fa01aktiviti))
			return false;
		if (fa01allo == null) {
			if (other.fa01allo != null)
				return false;
		} else if (!fa01allo.equals(other.fa01allo))
			return false;
		if (fa01comm == null) {
			if (other.fa01comm != null)
				return false;
		} else if (!fa01comm.equals(other.fa01comm))
			return false;
		if (fa01cp == null) {
			if (other.fa01cp != null)
				return false;
		} else if (!fa01cp.equals(other.fa01cp))
			return false;
		if (fa01daterec == null) {
			if (other.fa01daterec != null)
				return false;
		} else if (!fa01daterec.equals(other.fa01daterec))
			return false;
		if (fa01decr == null) {
			if (other.fa01decr != null)
				return false;
		} else if (!fa01decr.equals(other.fa01decr))
			return false;
		if (fa01desc == null) {
			if (other.fa01desc != null)
				return false;
		} else if (!fa01desc.equals(other.fa01desc))
			return false;
		if (fa01encr == null) {
			if (other.fa01encr != null)
				return false;
		} else if (!fa01encr.equals(other.fa01encr))
			return false;
		if (fa01fund == null) {
			if (other.fa01fund != null)
				return false;
		} else if (!fa01fund.equals(other.fa01fund))
			return false;
		if (fa01incr == null) {
			if (other.fa01incr != null)
				return false;
		} else if (!fa01incr.equals(other.fa01incr))
			return false;
		if (fa01jbt == null) {
			if (other.fa01jbt != null)
				return false;
		} else if (!fa01jbt.equals(other.fa01jbt))
			return false;
		if (fa01liab == null) {
			if (other.fa01liab != null)
				return false;
		} else if (!fa01liab.equals(other.fa01liab))
			return false;
		if (fa01mak1 == null) {
			if (other.fa01mak1 != null)
				return false;
		} else if (!fa01mak1.equals(other.fa01mak1))
			return false;
		if (fa01mak2 == null) {
			if (other.fa01mak2 != null)
				return false;
		} else if (!fa01mak2.equals(other.fa01mak2))
			return false;
		if (fa01module == null) {
			if (other.fa01module != null)
				return false;
		} else if (!fa01module.equals(other.fa01module))
			return false;
		if (fa01objek == null) {
			if (other.fa01objek != null)
				return false;
		} else if (!fa01objek.equals(other.fa01objek))
			return false;
		if (fa01pay == null) {
			if (other.fa01pay != null)
				return false;
		} else if (!fa01pay.equals(other.fa01pay))
			return false;
		if (fa01pk == null) {
			if (other.fa01pk != null)
				return false;
		} else if (!fa01pk.equals(other.fa01pk))
			return false;
		if (fa01ptj == null) {
			if (other.fa01ptj != null)
				return false;
		} else if (!fa01ptj.equals(other.fa01ptj))
			return false;
		if (fa01recby == null) {
			if (other.fa01recby != null)
				return false;
		} else if (!fa01recby.equals(other.fa01recby))
			return false;
		if (fa01seqc == null) {
			if (other.fa01seqc != null)
				return false;
		} else if (!fa01seqc.equals(other.fa01seqc))
			return false;
		if (fa01vote == null) {
			if (other.fa01vote != null)
				return false;
		} else if (!fa01vote.equals(other.fa01vote))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}