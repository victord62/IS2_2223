package es.unican.practica3;


public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros {

	public GestionSeguros(IClientesDAO daoContribuyentes, ISegurosDAO daoVehiculos) {
		// TODO
	}

	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Cliente nuevoCliente(Cliente c) {
		// TODO
		return null;
	}

	public Cliente bajaCliente(String dni) throws OperacionNoValida {
		// TODO
		return null;
	}

	public Cliente cliente(String dni) {
		// TODO
		return null;
	}

	public Seguro seguro(String matricula) {
		// TODO
		return null;
	}

}
