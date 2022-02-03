import { MatTableDataSource } from '@angular/material/table';
import { S_Transactioncode } from './../../model/transactioncode';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-new-survey',
  templateUrl: './new-survey.component.html',
  styleUrls: ['./new-survey.component.scss']
})
export class NewSurveyComponent implements OnInit {

  trcodecount: number;
  dataSource: MatTableDataSource<S_Transactioncode>
  columnsToDisplay: string[] = ['t_transactioncode', 't_is_used'];
  hidden: boolean = true;

  constructor(private httpClient: HttpClient) {
    this.dataSource = new MatTableDataSource<S_Transactioncode>();
   }

  ngOnInit(): void {
  }


  async getCodes(){
    this.hidden = false;
    const trcodes : S_Transactioncode[] = await this.httpClient.get<S_Transactioncode[]>(`${environment.backend_baseurl}/leosurvey/survey/add/` + this.trcodecount).toPromise();

    this.dataSource.data=[...trcodes];
  }


}
