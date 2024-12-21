export function stringParaEntradaDeData(data: string): string {
    if (!data) {
      return new Date().toISOString().split('T')[0]; // Data atual no formato ISO
    }
    return new Date(data).toISOString().split('T')[0]; // Apenas a data
  }
  
export function stringParaEntradaDeDataHora(dataHora: string): string {
if (!dataHora) {
    return new Date().toISOString().split('.')[0]; // Data e hora no formato ISO sem milissegundos
}
return new Date(dataHora).toISOString().split('.')[0]; // Data e hora
}

export function formatarData(data: string): string {
    const date = new Date(data);
    return date.toLocaleDateString("pt-BR"); // Formato: dd/mm/yyyy
}

export function formatarDataHora(dataHora: string): string {
    const date = new Date(dataHora);
    return date.toLocaleString("pt-BR", {
        dateStyle: "short",
        timeStyle: "short",
    }); // Formato: dd/mm/yyyy hh:mm
}