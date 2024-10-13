import { Component,Input,OnInit } from '@angular/core';
import { DossierMedicalService } from '../Services/dossier-medical.service';
import {CommonModule, DatePipe} from "@angular/common";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {AntecedantService} from "../Services/antecedant.service";
import {AddAntecedantComponent} from "../add-antecedant/add-antecedant.component";
import {AuthService} from "../Services/auth.service";


@Component({
  selector: 'app-dossier-medical',
  templateUrl: './dossier-medical.component.html',
  styleUrl: './dossier-medical.component.css',
  standalone: true,
    imports: [
        DatePipe,
        CommonModule,
        AddAntecedantComponent,
        RouterLink
    ],

})
export class DossierMedicalComponent implements OnInit{
  @Input() collaborateurId!: number;
  dossierMedical: any;
  antecedants: any[] = [];
  showAddForm: boolean = false;
  showAntecedants: boolean = false;
  currentUser: any = {};

  constructor(
    private route: ActivatedRoute,
    private dossierMedicalService: DossierMedicalService,
    private antecedantService: AntecedantService,
    private authService: AuthService
    ) {}
  ngOnInit(): void {
    this.collaborateurId = this.route.snapshot.params['collaborateurId'];
    this.dossierMedicalService.getDossierMedicalByCollaborateurId(this.collaborateurId).subscribe(data => {
      this.dossierMedical = data;
    });
    this.loadCurrentUser();
  }
  toggleAntecedants(): void {
    this.showAntecedants = !this.showAntecedants;
    if (this.showAntecedants) {
      this.antecedantService.getAntecedants(this.dossierMedical.idDossierMedical).subscribe(data => {
        this.antecedants = data;
      });
    }
  }

  openAddAntecedantForm(): void {
    this.showAddForm = true;
  }

  closeAddForm(): void {
    this.showAddForm = false;
  }
  logout() {
    this.authService.logout();
  }

  loadCurrentUser(){
    this.authService.getCurrentUser().subscribe({
      next: data => {

        this.currentUser = data;
      },
      error: error => {

      }
    });
  }

}
