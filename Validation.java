package Connectivity;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Validation")
public class Validation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Validation() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

//	Handling Post Requests 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String shopName = request.getParameter("shopName");
		String emailAddress = request.getParameter("emailAddress");
		String phoneNumber = request.getParameter("phoneNumber");
		String gender = request.getParameter("gender");
		String address = request.getParameter("address1") + request.getParameter("address2");
		String cityName = request.getParameter("cityName");
		String pinCode = request.getParameter("pinCode");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
//		Request Dispatcher for routing to 
		RequestDispatcher requestDispacher = null;
		String passwordNotEqual = "Passwords do not match";

		long phoneNumber1 = Long.parseLong(phoneNumber);
		long pincode1 = Long.parseLong(pinCode);

		if (DBconnection.doesAccountExists(emailAddress)) {
			
			response.sendRedirect("accountExists.html");

		} else if (matchPassword(password, confirmPassword)) {
			
			/* Now add the password in database */
			ShopInformation shopInformation = new ShopInformation(firstName, lastName, shopName, phoneNumber1,
					emailAddress, gender, address, cityName, pincode1, password);
			
			if (DBconnection.addAccount(shopInformation))
				response.sendRedirect("confirmation.html");
			} else {
				
			request.setAttribute("message", passwordNotEqual);
			requestDispacher = request.getRequestDispatcher("Error.jsp");
			requestDispacher.forward(request, response);
		}
	}

//	Verifying the firstName, lastName and shopName that it should be at least 3 characters long
	public static boolean verifyName(String textField) {
		if (textField.length() >= 3)
			return true;
		return false;
	}

//	Email Validation
	public static boolean verifyEmail(String email) {

		String regex = "[\\w]+[@][a-z]+[.][a-z]{2,3}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

//	Phone Number Validation
	public static boolean verifyPhoneNumber(String phoneNumber) {

		String regex = "[6-9]{1}[0-9]{9}";//      \\w -> any word character i.e. [a-zA-Z0-9];
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(phoneNumber);
		return matcher.matches();
	}

//	Password Matching
	public static boolean matchPassword(String password, String cnfrmPassword) {
		if (password.equals(cnfrmPassword))
			return true;

		return false;
	}

}
