--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: animal_shelter; Type: DATABASE; Schema: -; Owner: forestnewark
--

CREATE DATABASE animal_shelter WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE animal_shelter OWNER TO forestnewark;

\connect animal_shelter

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: animal; Type: TABLE; Schema: public; Owner: forestnewark
--

CREATE TABLE animal (
    animalid integer NOT NULL,
    name character varying(20) NOT NULL,
    species character varying(20) NOT NULL,
    breed character varying(20),
    description character varying(25)
);


ALTER TABLE animal OWNER TO forestnewark;

--
-- Name: animal_animalid_seq; Type: SEQUENCE; Schema: public; Owner: forestnewark
--

CREATE SEQUENCE animal_animalid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE animal_animalid_seq OWNER TO forestnewark;

--
-- Name: animal_animalid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: forestnewark
--

ALTER SEQUENCE animal_animalid_seq OWNED BY animal.animalid;


--
-- Name: animal animalid; Type: DEFAULT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY animal ALTER COLUMN animalid SET DEFAULT nextval('animal_animalid_seq'::regclass);


--
-- Data for Name: animal; Type: TABLE DATA; Schema: public; Owner: forestnewark
--

COPY animal (animalid, name, species, breed, description) FROM stdin;
\.


--
-- Name: animal_animalid_seq; Type: SEQUENCE SET; Schema: public; Owner: forestnewark
--

SELECT pg_catalog.setval('animal_animalid_seq', 21, true);


--
-- Name: animal animal_pkey; Type: CONSTRAINT; Schema: public; Owner: forestnewark
--

ALTER TABLE ONLY animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (animalid);


--
-- Name: animal_animalid_uindex; Type: INDEX; Schema: public; Owner: forestnewark
--

CREATE UNIQUE INDEX animal_animalid_uindex ON animal USING btree (animalid);


--
-- PostgreSQL database dump complete
--

