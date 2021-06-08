package evaluation.candidate.restapi2.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * @author Raymond
 */
public class IdNumber {
	private String idNo;
	private int age;
	private String gender;
	private LocalDate dateOfBirth;

	public IdNumber(String idNo) {
		this.idNo = idNo;
	}

	/**
	 *
	 */
	public boolean validateIdNumber() {

		if (idNo.trim().length() != 13) {
			return false;        // Invalid length
		}

		if (!Pattern.matches("[0-9]*", idNo)) {
			return false;        // Not numeric
		}

		if (!checkIDNumber()) {
			return false;        // Modulus calc failed
		}

		if (!checkDate()) {
			return false;
		}

		age = calcAge();
		gender = calcGender();
		dateOfBirth = calcDateOfBirth();

		return true;
	}

	private boolean checkDate() {
		int months;
		int days;
		months = Integer.parseInt(idNo.substring(2, 4));
		days = Integer.parseInt(idNo.substring(4, 6));

		return (months > 0 && months < 13) && (days > 0 && days < 32);

	}

	private boolean checkIDNumber() {
		int total = 0;
		int num;

		for (int i = 0; i < 13; i++) {
			num = Integer.parseInt(idNo.substring(i, i + 1));
			if (((i + 1) % 2) == 1) {
				total += num;
			} else {
				total += num * 2;
				if ((num * 2) > 9) {
					total -= 9;
				}
			}
		}
		return (total % 10) <= 0;
	}

	int calcAge() {

		LocalDate curDate = LocalDate.now();

		int calcAge;
		int curDay = curDate.getDayOfMonth();
		int curMonth = curDate.getMonthValue();
		int curYear = curDate.getYear();
		int birthDay = Integer.parseInt(idNo.substring(4, 6));
		int birthMonth = Integer.parseInt(idNo.substring(2, 4));
		int birthYear = Integer.parseInt(idNo.substring(0, 2));

		if (birthYear < 20) {
			birthYear += 2000;
		} else {
			birthYear += 1900;
		}

		calcAge = curYear - birthYear;

		if (curMonth < birthMonth) {
			calcAge--;
		}
		if (curMonth == birthMonth && curDay < birthDay) {
			calcAge--;
		}

		this.age = calcAge;
		return calcAge;
	}

	String calcGender() {
		long genderCode = Integer.parseInt(idNo.substring(6, 10));

		if (genderCode > 5000) {
			gender = "M";
		} else {
			gender = "F";
		}

		return gender;
	}

	LocalDate calcDateOfBirth() {
		int birthDay = Integer.parseInt(idNo.substring(4, 6));
		int birthMonth = Integer.parseInt(idNo.substring(2, 4));
		int birthYear = Integer.parseInt(idNo.substring(0, 2));

		if (birthYear < 20) {
			birthYear += 2000;
		} else {
			birthYear += 1900;
		}
		return LocalDate.of(birthYear, birthMonth, birthDay);
	}

	public String getIDNumber() {
		return idNo;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
}