import Evento from "@/core/Evento"
import { IconeEdicao, IconeLixo } from "../icones/tabela"
import { formatarData, formatarDataHora } from "@/utils/converters";

interface TabelaProps {
    eventos: Evento[]
    eventoSelecionado?: (evento: Evento) => void
    eventoExcluido?: (evento: Evento) => void
}

export default function Tabela(props: TabelaProps) {
    
    const exibirAcoes = props.eventoSelecionado || props.eventoExcluido

    function renderHeader() {
        return (
            <tr>
                <th className="text-left p-3">ID</th>
                <th className="text-left p-3">Nome</th>
                <th className="text-left p-3">Data</th>
                <th className="text-left p-3">Data Início Inscrição</th>
                <th className="text-left p-3">Data Fim Inscrição</th>
                <th className="text-left p-3">Descrição</th>
                <th className="text-left p-3">Status</th>
                {exibirAcoes ? <th className="p-3">Ações</th> : false}
            </tr>
        )
    }

    function renderDados() {
        return props.eventos?.map((evento, i) => {
            return (
                <tr key={evento.id}
                    className={`${i % 2 === 0 ? 'bg-indigo-200' : 'bg-indigo-100'} `}>
                    <td className="text-left p-3">{evento.id}</td>
                    <td className="text-left p-3">{evento.nome}</td>
                    <td className="text-left p-3">{formatarData(evento.data)}</td>
                    <td className="text-left p-3">{formatarDataHora(evento.dataInicioInsc)}</td>
                    <td className="text-left p-3">{formatarDataHora(evento.dataFimInsc)}</td>
                    <td className="text-left p-3">{evento.descricao}</td>
                    <td className="text-left p-3">{evento.status}</td>
                    {exibirAcoes 
                    ? renderizarAcoes(evento)
                    : false }
                </tr>
            )
        })
    }

    function renderizarAcoes(evento: Evento) {
        return (
            <td className="flex justify-center">
                {props.eventoSelecionado ? (
                    <button onClick={() => props.eventoSelecionado?.(evento)} className={`flex justify-center items-center
                    text-green-600 rounded-full p-2 m-1
                    hover:bg-gray-100`}>{IconeEdicao}</button>
                ) : false }
                {props.eventoExcluido ? (
                    <button onClick={() => props.eventoExcluido?.(evento)} className={`flex justify-center items-center
                    text-red-600 rounded-full p-2 m-1
                    hover:bg-gray-100`}>{IconeLixo}</button>
                ) : false}
            </td>
        )
    }

    return (
        <table className="min-w-full rounded-xl overflow-hidden border-collapse table-auto">
            <thead className={`text-gray-100
            bg-gradient-to-r from-indigo-500 to-indigo-800`}>
                {renderHeader()}
            </thead>
            <tbody>
                {renderDados()}
            </tbody>
        </table>
    )
}
