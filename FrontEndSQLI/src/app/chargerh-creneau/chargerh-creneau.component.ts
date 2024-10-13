import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AuthService} from "../Services/auth.service";
import {HttpClient} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {TypesVisite} from "../types-visite.enum";

@Component({
  selector: 'app-chargerh-creneau',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    CommonModule
  ],
  templateUrl: './chargerh-creneau.component.html',
  styleUrl: './chargerh-creneau.component.css'
})
export class ChargerhCreneauComponent implements OnInit{
  currentUser: any = {};
  medecins: any[] = [];
  collaborateurs: any[] = [];
  visitsToConfirm: any[] = [];
  selectedMedecinId: string = '';
  selectedCollaborateurId: string = '';
  isCreneauModalOpen: boolean = false;
  isConfirmFormOpen: boolean = false;
  confirmFormData: { visiteId: number | null, motif: string, typesVisite: TypesVisite | '' } = {
    visiteId: null,
    motif: '',
    typesVisite: ''
  };
  typesVisiteOptions = Object.values(TypesVisite);

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.loadCurrentUser().then(()=> {
      this.loadMedecins();
      this.loadCollaborateurs();
      this.loadVisitsToConfirm();
    });
  }

  async loadCurrentUser(){
    return new Promise<void>((resolve, reject) => {
      this.authService.getCurrentUser().subscribe({
        next: data => {

          this.currentUser = data;
          resolve();
        },
        error: error => {

          reject(error);
        }
      });
    });
  }

  loadMedecins() {
    this.http.get('http://localhost:8080/chargerh/availableDoctors').subscribe({
      next: (data:any) => {
        this.medecins = data;
      },
      error: (error) => {

      }
    });
  }

  loadCollaborateurs(){
    this.http.get('http://localhost:8080/chargerh/collaborateursdispo').subscribe({
      next: (data:any) => {
        this.collaborateurs = data;
      },
      error: (error) => {

      }
    });
  }

  loadVisitsToConfirm() {
    // Check if currentUser is loaded
    if (!this.currentUser || !this.currentUser.idUser) {

      this.loadCurrentUser().then(() => this.loadVisitsToConfirm());
      return;
    }

    const chargeRHId = this.currentUser.idUser;
    this.http.get(`http://localhost:8080/chargerh/visitsToConfirm?chargéRHId=${chargeRHId}`).subscribe({
      next: (data: any) => {
        this.visitsToConfirm = data;
      },
      error: (error) => {

      }
    });
  }

  openConfirmForm(visit: any) {
    if (visit && visit.idVisite) {
      this.confirmFormData.visiteId = visit.idVisite;
      this.isConfirmFormOpen = true;
    } else {

    }
  }

  closeConfirmForm() {
    this.isConfirmFormOpen = false;
    this.confirmFormData = {
      visiteId: null,
      motif: '',
      typesVisite: ''
    };
  }

  submitConfirmForm() {
    if (!this.confirmFormData.visiteId) {

      return;
    }

    if (confirm("Are you sure you want to confirm this visit with the entered details?")) {
      const { visiteId, motif, typesVisite } = this.confirmFormData;
      this.http.post(`http://localhost:8080/chargerh/confirmVisit?visiteId=${visiteId}&motif=${motif}&typesVisite=${typesVisite}`, {}).subscribe({
        next: response => {

          this.closeConfirmForm();
          this.loadVisitsToConfirm();
        },
        error: error => {

        }
      });
    }
  }

  cancelVisit(visitId: number){
    if(confirm("Are you sure you want to cancel this visit?")) {
      this.http.post(`http://localhost:8080/chargerh/cancelVisit?visiteId=${visitId}`, {}).subscribe({
        next: response => {

          this.loadVisitsToConfirm();
        },
        error: error => {

        }
      });
    }
  }


  openCreneauModal(){
    this.isCreneauModalOpen = true;
  }

  closeCreneauModal() {
    this.isCreneauModalOpen = false;
  }

  submitCreneau(){
    const medecinId = Number(this.selectedMedecinId);
    const chargeRHId = this.currentUser.idUser;
    const collaborateurId = Number(this.selectedCollaborateurId)

    const url = `http://localhost:8080/chargerh/assignCreneaux?medecinId=${medecinId}&chargeRHId=${chargeRHId}&collaborateurId=${collaborateurId}`;

    this.http.post(url, {}).subscribe({
      next: (response) => {

        this.closeCreneauModal();
      },
      error: (error) => {

      }
    });
    window.location.reload();
  }

  logout() {
    this.authService.logout();
  }


}
