import { stringParaEntradaDeData, stringParaEntradaDeDataHora } from "@/utils/converters";

export default class Evento {
  id: number | null;
  nome: string;
  data: string; // LocalDate no backend
  descricao: string;
  status: string;
  dataInicioInsc: string; // LocalDateTime no backend
  dataFimInsc: string; // LocalDateTime no backend

  constructor(
    id: number | null,
    nome: string,
    data: string,
    descricao: string,
    status: string,
    dataInicioInsc: string,
    dataFimInsc: string
  ) {
    this.id = id;
    this.nome = nome;
    this.data = stringParaEntradaDeData(data); // Converte para entrada no formato ISO
    this.descricao = descricao;
    this.status = status;
    this.dataInicioInsc = stringParaEntradaDeDataHora(dataInicioInsc); // Converte para entrada no formato ISO
    this.dataFimInsc = stringParaEntradaDeDataHora(dataFimInsc); // Converte para entrada no formato ISO
  }


    static geraEventosMock() {
        return [
            new Evento(
                1,
                "UPF em Dança",
                "10/11/2024",
                "Evento de dança",
                "PREVISTO",
                "01/11/2024",
                "09/11/2024"
            ),
            new Evento(
                2,
                "UPF na feitech",
                "10/11/2024",
                "Evento de tecnologia",
                "PREVISTO",
                "01/11/2024",
                "09/11/2024"
            )
        ];
    }

    static vazio(): Evento {
      return new Evento(
        null,
        "",
        "",
        "",
        "PREVISTO",
        "",
        ""
      );
    }
}
