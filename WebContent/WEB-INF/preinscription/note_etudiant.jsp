<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <section class="tables">   
            <div class="container-fluid">
              <div class="row">
                <div class="col-lg-12">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle">
                          <i class="fa fa-ellipsis-v"></i>
                        </button>
                        <div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow">
                          <a href="#" class="dropdown-item remove"><i class="fa fa-times"></i>Close</a>
                          <a href="#" class="dropdown-item edit"><i class="fa fa-gear"></i>Edit</a>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-12">
                         <div class="card-header d-flex align-items-center">
                          <h4 class="h4">Notes des étudiants en :</h4>  ${niveau} ${nom_parcours}
                        </div>
                      </div>
                      <!-- <div class="col-md-4">
                         <form method="post" action="pdf_list_etudiant">
                          <input type="hidden" name="niveauEtu" value="${niveau}">
                          <input type="hidden" name="parcoursEtu" value="${nom_parcours}">
                          <input type="submit" value="Exporter" class="btn btn-primary">
                        </form>
                      </div>  -->
                    </div>

                    <table class="table">
                      <thead>
                        <tr>
                          <th></th>
                          <th>Nom</th>
                          <th>Prénom</th>
                          <th>Semèstre</th>
                          <th>ECUE</th>
                          <th>Examen1</th>
                          <th>Rattrapage1</th>
                          <th>Enregistrement</th>
<!--                           <th>Examen2</th>
                          <th>Rattrapage2</th> -->
                          <!-- <th>Détail</th> -->
                        </tr>
                      </thead>
                      <tbody>
                        <c:forEach var="etudiant" items="${etudiants}">
                          <tr>
                              <td>
                                <input type="hidden" name="${etudiant.id_parcours}">
                              </td>
                              <td>${etudiant.nom}</td>
                              <td>${etudiant.prenom}</td>
                              <td>
                                  <div class="form-group row">
                                    <div class="col-lg-12">
                                    <select class="custom-select">
                                      <option selected>Choix</option>
                                      <option>Pair</option>
                                      <option>Impair</option>
                                    </select>
                                    </div>
                                  </div>
                              </td>
                              <td>
                                  <div class="form-group row">
                                    <div class="col-lg-12">
                                    <select class="custom-select">
                                      <option selected>Matière</option>
                                      <option>...</option>
                                    </select>
                                    </div>
                                  </div>
                              </td>
                              <td>
                                <form method="post" action="note_etudiant">
                                  <div class="btn-group">
                                    <input type="number" size="2" step="0.25" value="0" min="0" max="20" style="width: 75px;">
                                </div>
                                </form>
                              </td>
                              <td>
                                <form method="post" action="note_etudiant">
                                  <div class="btn-group">
                                    <input type="number" size="2" step="0.25" value="0" min="0" max="20" style="width: 75px;">
                                </div>
                                </form>
                              </td>
                              <td>
                                <form method="post" action="note_etudiant">
                                    <input type="submit" value="save" class="btn btn-success">
                                </form>
                              </td>
                              <!-- <td>
                                 <form method="post" action="cursus">
                                    <input type="hidden" name="cursus" value="${etudiant.id_etudiant}">
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#ecue_${etudiant.id_etudiant}">ECUE
                                    </button>
                                </form>
                              </td> -->
                              <!-- <td>
                                 <form method="post" action="carte">
                                    <input type="hidden" name="CE" value="${etudiant.id_etudiant}" />
                                    <button type="button" class="btn btn-primary modaly" data-toggle="modal" data-target="#ue_${etudiant.id_etudiant}">UE</button>
                                </form>
                              </td> -->
                          </tr>
                      </c:forEach>
                      </tbody>                        
                   </table>
                </div>
            </div>
        </div>
    </div>
</section> 
  <c:forEach var="etudiant" items="${etudiants}">
            <!-- <div class="modal fade bd-example-modal-lg" id="details_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenom}</h3>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">

                            <div class="row">
                                <div class="col-md-4">
                                    EU : 
                                </div>
                                <div class="col-md-4">
                                    ECUE : 
                                </div>
                                <div class="col-md-4">
                                    Note examen : ../20 
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    Note rattrapage : ../20
                                </div>
                                <div class="col-md-6">
                                    Total : ../20
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    Année universitaire : ${etudiant.annee_u}
                                </div>
                                <div class="col-md-6">
                                    Situation : ${etudiant.situation}
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        </div>
                    </div>
                </div>
            </div> -->
            <!-- Les cursus des étudiants -->
 <!--  <div class="modal fade bd-example-modal-lg" id="ecue_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenom}</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="row">
	                                <div class="col-md-4">
	                                    Nom ECUE : 
	                                </div>
	                                <div class="col-md-4">
	                                    Credit UE : ../30 
	                                </div>
                           		</div>	                            
	                            <div class="row">
	                                <div class="col-md-6">
	                                    Année universitaire : ${etudiant.annee_u}
	                                </div>
	                                <div class="col-md-6">
	                                    Situation : ${etudiant.situation}
	                                </div>
	                            </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>    
                </div> -->
<!-- Carte des étudiants -->
<!-- <div class="modal fade bd-example-modal-lg" id="ue_${etudiant.id_etudiant}" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
                   <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title" id="ModalLabel">${etudiant.nom} ${etudiant.prenom}</h3>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
    							<div class="row">
	                                <div class="col-md-4">
	                                    Nom UE : 
	                                </div>
	                                <div class="col-md-4">
	                                    Credit UE : ../30 
	                                </div>
                           		</div>	                            
	                            <div class="row">
	                                <div class="col-md-6">
	                                    Année universitaire : ${etudiant.annee_u}
	                                </div>
	                                <div class="col-md-6">
	                                    Situation : ${etudiant.situation}
	                                </div>
	                            </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                            </div>
                        </div>
                    </div>
</div> -->
</c:forEach>