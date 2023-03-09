package es.unican.practica3;



public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoContribuyentes = new ClientesDAO();
		ISegurosDAO daoVehiculos = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoContribuyentes, daoVehiculos);
		// VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		VistaAgente vista = new VistaAgente(null, null, null);
		vista.setVisible(true);

	}

}
