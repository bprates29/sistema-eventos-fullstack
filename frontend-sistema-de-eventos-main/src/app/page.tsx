'use client';

import React, { useState } from 'react';
import axios from 'axios';
import { useRouter } from 'next/navigation'; // Importa o hook para redirecionar

const BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080';

export default function Home() {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({
    nome: '',
    senha: '',
    email: '',
  });
  const [message, setMessage] = useState('');
  const router = useRouter(); // Hook para redirecionar

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleLogin = async () => {
    try {
      const response = await axios.post(`${BASE_URL}/auth/login`, {
        login: formData.email,
        password: formData.senha,
      });
      const token = response.data.token;
  
      if (!token) {
        throw new Error("Token não recebido");
      }
  
      localStorage.setItem('token', token);
      setMessage('Login realizado com sucesso!');
      router.push('/eventos'); // Redireciona para /eventos após o login
    } catch (error) {
      console.error('Erro ao fazer login:', error);
      setMessage('Erro ao fazer login. Verifique suas credenciais.');
    }
  };

  const handleRegister = async () => {
    try {
      await axios.post(`${BASE_URL}/auth/register`, {
        nome: formData.nome,
        senha: formData.senha,
        email: formData.email,
      });
      setMessage('Cadastro realizado com sucesso! Agora você pode fazer login.');
      setIsLogin(true);
    } catch (error) {
      console.error('Erro ao cadastrar usuário:', error);
      setMessage('Erro ao cadastrar usuário. Tente novamente.');
    }
  };

  const toggleForm = () => {
    setIsLogin(!isLogin);
    setMessage('');
    setFormData({ nome: '', senha: '', email: '' });
  };

  return (
    <div style={{ padding: '2rem', maxWidth: '400px', margin: 'auto', fontFamily: 'Arial, sans-serif' }}>
      <h2 style={{ textAlign: 'center', marginBottom: '1rem' }}>Sistema de Eventos</h2>

      {isLogin ? (
        <>
          <h3>Login</h3>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              handleLogin();
            }}
            style={{ display: 'flex', flexDirection: 'column', gap: '0.5rem' }}
          >
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                style={{ width: '100%', padding: '0.5rem', marginTop: '0.2rem' }}
              />
            </label>
            <label>
              Senha:
              <input
                type="password"
                name="senha"
                value={formData.senha}
                onChange={handleChange}
                required
                style={{ width: '100%', padding: '0.5rem', marginTop: '0.2rem' }}
              />
            </label>
            <button type="submit" style={{ padding: '0.7rem', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Entrar
            </button>
          </form>
        </>
      ) : (
        <>
          <h3>Cadastro</h3>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              handleRegister();
            }}
            style={{ display: 'flex', flexDirection: 'column', gap: '0.5rem' }}
          >
            <label>
              Nome:
              <input
                type="text"
                name="nome"
                value={formData.nome}
                onChange={handleChange}
                required
                style={{ width: '100%', padding: '0.5rem', marginTop: '0.2rem' }}
              />
            </label>
            <label>
              Email:
              <input
                type="email"
                name="email"
                value={formData.email}
                onChange={handleChange}
                required
                style={{ width: '100%', padding: '0.5rem', marginTop: '0.2rem' }}
              />
            </label>
            <label>
              Senha:
              <input
                type="password"
                name="senha"
                value={formData.senha}
                onChange={handleChange}
                required
                style={{ width: '100%', padding: '0.5rem', marginTop: '0.2rem' }}
              />
            </label>
            <button type="submit" style={{ padding: '0.7rem', backgroundColor: '#28a745', color: 'white', border: 'none', borderRadius: '5px', cursor: 'pointer' }}>
              Cadastrar
            </button>
          </form>
        </>
      )}

      <button
        onClick={toggleForm}
        style={{ marginTop: '1rem', backgroundColor: 'transparent', color: '#007bff', border: 'none', cursor: 'pointer' }}
      >
        {isLogin ? 'Ainda não tem conta? Cadastre-se' : 'Já tem conta? Faça login'}
      </button>

      {message && <p style={{ color: 'red', marginTop: '1rem', textAlign: 'center' }}>{message}</p>}
    </div>
  );
}