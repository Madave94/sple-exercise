import java.net.*;

public aspect Pointcuts {
	// a) execution of Client send(text)
	pointcut a() : execution(void client.Client.send(*));
	// b) invocation of method send
	pointcut b() : call(void *..*.send(..));
	// c) any execution of a public method
	pointcut c() : execution(public * *..*(..));
	// d) every invocation of a method send in class Client not originating in the class Client itself
	pointcut d() : call(void *..*.send(..)) && !within(client.Client);
	// e) every read-access of the socket on the Server
	pointcut e() : get(ServerSocket server.Server.server);
	// f) every instantiation of class Connection
	pointcut f() : initialization(server.Connection.new(..));
	// g) every method invocation inside the method Server.broadcast() unless the target is an instance of class Connection
	pointcut g() : call(void server.Server.broadcast(..)) && !within(server.Connection);
}
