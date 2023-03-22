package es.unican.is2;


public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros {
	private IClientesDAO clientesDao;
	private ISegurosDAO segurosDao;

	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		this.clientesDao = daoContribuyentes;
		this.segurosDao = daoVehiculos;
	}

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		Cliente cliente = clientesDao.cliente(dni);
		
		if (cliente == null) {
			throw new OperacionNoValida("No hay un cliente con el DNI " + dni + "indicado");
		}
		
		Seguro seguro = segurosDao.seguro(s.getMatricula());
		
		if (seguro != null) {
			throw new OperacionNoValida("Hay un seguro existente con la matrícula" + s.getMatricula() + "indicada");
		}
		
		cliente.getSeguros().add(seguro);
		segurosDao.creaSeguro(seguro);
		
		return s;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		Cliente cliente = clientesDao.cliente(dni);
		
		if (cliente == null) {
			throw new OperacionNoValida("No hay un cliente con el DNI " + dni + "indicado");
		}
		
		Seguro seguro = segurosDao.seguro(matricula);
		
		if (seguro == null) {
			throw new OperacionNoValida("No hay un seguro existente con la matrícula" + matricula + "indicada");
		}
		
		if(!cliente.getSeguros().contains(seguro)) {
			throw new OperacionNoValida("El seguro con " + matricula + "indicada no pertenece al cliente con DNI" + dni);
		}
		
		cliente.getSeguros().remove(seguro);	
		segurosDao.eliminaSeguro(matricula);	
		return seguro;
	}

	public Cliente nuevoCliente(Cliente c) {
		return clientesDao.creaCliente(c);
	}

	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		Cliente cliente = clientesDao.cliente(dni);
		
		if (cliente == null) {
			throw new OperacionNoValida("No hay un cliente con el DNI " + dni + "indicado");
		}
		
		clientesDao.eliminaCliente(dni);
		return cliente;
	}

	public Cliente cliente(String dni) {
		return clientesDao.cliente(dni);
	}

	public Seguro seguro(String matricula) {
		return segurosDao.seguro(matricula);
	}

}
