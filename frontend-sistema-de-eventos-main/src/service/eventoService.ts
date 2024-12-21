import axios from 'axios';
import Evento from '../core/Evento';

interface ApiResponse {
  content: Evento[];
}

const BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

// Helper para obter o token JWT armazenado
const getAuthToken = (): string | null => {
  return localStorage.getItem('token'); // ou onde você armazenar o token
};

// Configurar cabeçalhos com o token JWT
const getAuthHeaders = () => {
  const token = getAuthToken();
  return token ? { Authorization: `Bearer ${token}` } : {};
};

export const fetchEventos = async (): Promise<Evento[]> => {
  try {
    const response = await axios.get<ApiResponse>(`${BASE_URL}/eventos`, {
      headers: getAuthHeaders(),
    });
    return response.data.content;
  } catch (error) {
    console.error('Erro ao buscar eventos:', error);
    throw new Error('Erro ao buscar eventos');
  }
};

export const cadastrarEvento = async (evento: Evento): Promise<Evento> => {
  try {
    const response = await axios.post<Evento>(`${BASE_URL}/eventos`, evento, {
      headers: getAuthHeaders(),
    });
    return response.data;
  } catch (error) {
    console.error("Erro ao cadastrar evento:", error);
    throw error;
  }
};

export const atualizarEvento = async (evento: Evento): Promise<Evento> => {
  try {
    const response = await axios.put<Evento>(`${BASE_URL}/eventos/${evento.id}`, evento, {
      headers: getAuthHeaders(),
    });
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar evento:", error);
    throw error;
  }
};

export const excluirEvento = async (id: number): Promise<void> => {
  try {
    await axios.delete(`${BASE_URL}/eventos/${id}`, {
      headers: getAuthHeaders(),
    });
  } catch (error) {
    console.error("Erro ao excluir evento:", error);
    throw error;
  }
};
