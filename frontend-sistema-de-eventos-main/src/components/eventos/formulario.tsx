import Evento from "@/core/Evento";
import Entrada from "./entrada";
import { useState } from "react";
import { stringParaEntradaDeData, stringParaEntradaDeDataHora } from "@/utils/converters";
import Botao from "./botao";

interface FormularioProps {
  evento: Evento;
  eventoMudou?: (evento: Evento) => void;
  cancelado?: () => void;
}

export default function Formulario(props: FormularioProps) {
  const id = props.evento?.id;
  const [nome, setNome] = useState(props.evento?.nome || "");
  const [data, setData] = useState(props.evento?.data || "");
  const [dataInicioInsc, setDataInicioInsc] = useState(props.evento?.dataInicioInsc || "");
  const [dataFimInsc, setDataFimInsc] = useState(props.evento?.dataFimInsc || "");
  const [descricao, setDescricao] = useState(props.evento?.descricao || "");
  const [status, setStatus] = useState(props.evento?.status || "PREVISTO");

  return (
    <div>
      {id && <Entrada texto="ID" valor={id} somenteLeitura />}
      <Entrada texto="Nome" valor={nome} onChange={setNome} />
      <Entrada
        texto="Data do Evento"
        tipo="date"
        valor={stringParaEntradaDeData(data)}
        onChange={(valor) => setData(valor)} // Já vem no formato esperado para `date`
        />
      <Entrada
        texto="Data Início Inscrição"
        tipo="datetime-local"
        valor={stringParaEntradaDeDataHora(dataInicioInsc)}
        onChange={(valor) => setDataInicioInsc(valor)} // Já vem no formato esperado para `datetime-local`
        />
      <Entrada
        texto="Data Fim Inscrição"
        tipo="datetime-local"
        valor={stringParaEntradaDeDataHora(dataFimInsc)}
        onChange={(valor) => setDataFimInsc(valor)} // Já vem no formato esperado para `datetime-local`
        />
      <Entrada texto="Descrição" valor={descricao} onChange={setDescricao} />
      <div className="mb-4">
        <label className="block text-gray-700 text-sm font-bold mb-2">Status</label>
        <select
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          className="block w-full px-4 py-2 text-gray-700 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring focus:ring-blue-300"
        >
          <option value="PREVISTO">PREVISTO</option>
          <option value="ABERTO">ABERTO</option>
          <option value="CANCELADO">CANCELADO</option>
          <option value="ENCERRADO">ENCERRADO</option>
        </select>
      </div>

      <div className="flex justify-end mt-5">
        <Botao
          className="mr-3"
          cor="bg-gradient-to-r from-blue-500 to-blue-700"
          onClick={() =>
            props.eventoMudou?.(
              new Evento(id, nome, data, descricao, status, dataInicioInsc, dataFimInsc)
            )
          }
        >
          {id ? "Alterar" : "Salvar"}
        </Botao>
        <Botao cor="bg-gradient-to-r from-gray-500 to-gray-700" onClick={props.cancelado}>
          Cancelar
        </Botao>
      </div>
    </div>
  );
}
