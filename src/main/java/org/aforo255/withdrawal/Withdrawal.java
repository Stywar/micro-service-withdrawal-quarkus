package org.aforo255.withdrawal;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aforo255.withdrawal.entity.Transaction;
import org.aforo255.withdrawal.service.ITransaction;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
@Path("/retiro")
public class Withdrawal {
	
	@Inject
	ITransaction service;

	@Inject
	@Channel("transactions")
	Emitter<Transaction> emitter;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Counted(name="contador_transaction")
	@Timed(name="duracion_transaction")
	
	public Response saveTransaction(Transaction transaction) {
		System.out.println("Ingresando saveTransaction ");
		Transaction trx = null;
		Map<String, Object> response = new HashMap<>();
		try { 
			System.out.println("antes trx " +transaction.getType());
			trx = service.save(transaction);
			System.out.println("despues trx ");
		}catch(Exception ex ) {
			System.out.println("Mostrando mensaje  :"+ex.getMessage().toString());
			
		}
		
		emitter.send(trx);
		response.put("mensaje", "Transaccion realizada con exito!!");
		response.put("trandaction", trx);
		return Response.status(Status.CREATED).entity(response).build();

	}

}
