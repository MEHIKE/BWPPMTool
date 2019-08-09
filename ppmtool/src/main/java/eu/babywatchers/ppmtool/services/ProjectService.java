package eu.babywatchers.ppmtool.services;

import eu.babywatchers.ppmtool.domain.Project;
import eu.babywatchers.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateproject(Project project) {
        return projectRepository.save(project);
    }

}
