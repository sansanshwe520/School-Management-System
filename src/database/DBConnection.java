package database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

private DBConnection() {
		
	}
	
	public static Connection getConnection(){
		
		String url  = "jdbc:mysql://localhost:3306/shoolmanagementsystemdb?allowPublicKeyRetrieval=true&useSSL=false";
		String user = "bibliophile";
		String password ="w$n8@minWn";
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(url, user, password);
//			System.out.println("Connection is success"+(connection != null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}











<div class="container">
		<div class="row">
				<div class="col-md-8 mx-auto">
				<div class="card details-card mb-3">
					<img src="${book.image }" class="card-img-top details-card-image" alt="${book.title }">
					<div class="card-body text-center">
						<h5 class="card-title">${book.title }</h5>
						<p class="card-text">category : ${book.category }</p>
						<p class="card-text">price : ${book.price }</p>
						<p class="card-text">quantity : ${book.quantity }</p>
						<p class="card-text">subTotal : $ ${book.subTotal }</p>
						<p class="card-text">issuedDate : ${book.issuedDate }</p>
						<p class="card-text">essential : ${book.essential ? 'Yes':'No' }</p>
						<h3>Book Description : </h3>
						<p class="card-text">${book.description }</p>
						
						<c:url var="updateLink" value="book">
							<c:param name="mode" value="LOAD"/>
							<c:param name="bookId" value="${book.id }"/>
						</c:url>
						
						<c:url var="deleteLink" value="book">
							<c:param name="mode" value="DELETE"/>
							<c:param name="bookId" value="${book.id }"/>
						</c:url>
						<c:if test="${user.role == 'admin'}">
						<a href="${updateLink }" class="btn btn-info"><i class="bi bi-pencil"></i> Update</a>
						<a href="${deleteLink }" class="btn btn-danger"><i class="bi bi-x-circle"></i> Delete</a>
	
		  				</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>




.list-card-image{
	height: 20rem;
}

.list-card{
	width: 18rem;
	height: 32rem;
}

.details-card-image{
	height: 50rem;
}