package eu.babywatchers.ppmtool.services;

import eu.babywatchers.ppmtool.domain.Project;
import eu.babywatchers.ppmtool.exceptions.ProjectIdException;
import eu.babywatchers.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateproject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists!");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project=projectRepository.findByProjectIdentifier(projectId);

        if (project==null) {
            throw new ProjectIdException("Project ID '"+projectId.toUpperCase()+"' does't exists!");
        }
        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project==null)  {
            throw new ProjectIdException("Can't delete project with ID '"+projectId+"'. This project does't exist ");
        }
        projectRepository.delete(project);
    }
}
