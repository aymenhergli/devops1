package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {
	private static final Logger LOGGER = LogManager.getLogger(EntrepriseServiceImpl.class);

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			LOGGER.debug("Trait for dev");
			LOGGER.info("Added a new entreprise successfully");
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
			throw e;
		}

	}

	@Override
	public List<Entreprise> findAll() {
		return (List<Entreprise>) entrepriseRepoistory.findAll();
	}

	public int ajouterDepartement(Departement dep) {
		try {
			LOGGER.debug("Trait for dev");
            LOGGER.info("Added a new departement successfully ");
			deptRepoistory.save(dep);
			return dep.getId();
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
			throw e;
		}
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		        LOGGER.info("affect a departement to entreprise");
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
				Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);

				assert depManagedEntity != null;
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		try {
			LOGGER.debug("Returned Depatement list successfully");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		List<String> depNames = new ArrayList<>();
			assert entrepriseManagedEntity != null;
			for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
		}
		catch (Exception e)
		{
			LOGGER.error(e.getMessage());
			throw e;
		}
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(Objects.requireNonNull(entrepriseRepoistory.findById(entrepriseId).orElse(null)));
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(Objects.requireNonNull(deptRepoistory.findById(depId).orElse(null)));
	}


	public Entreprise getEntrepriseById(int entrepriseId) {

		return entrepriseRepoistory.findById(entrepriseId).orElse(null);
	}

}
