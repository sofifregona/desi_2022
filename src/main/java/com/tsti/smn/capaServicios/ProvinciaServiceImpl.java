/**
 * @author kuttel
 *
 */
package com.tsti.smn.capaServicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsti.smn.capaDaos.IProvinciaRepo;
import com.tsti.smn.pojos.Provincia;

@Service
public class ProvinciaServiceImpl implements ProvinciaService {
//	Logger LOG = LoggerFactory.getLogger(CiudadService.class);
//	
	@Autowired
	IProvinciaRepo repo;

	@Override
	public List<Provincia> getAll() {
		return repo.findAll();
	}



	@Override
	public Provincia getById(Long idCiudad) {
		return repo.findById(idCiudad).get();
	}

}
