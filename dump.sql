--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: assignment3; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA assignment3;


ALTER SCHEMA assignment3 OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: bill; Type: TABLE; Schema: assignment3; Owner: postgres
--

CREATE TABLE assignment3.bill (
    id integer NOT NULL,
    orderid integer,
    creation_date timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    total_price integer
);


ALTER TABLE assignment3.bill OWNER TO postgres;

--
-- Name: bill_id_seq; Type: SEQUENCE; Schema: assignment3; Owner: postgres
--

CREATE SEQUENCE assignment3.bill_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE assignment3.bill_id_seq OWNER TO postgres;

--
-- Name: bill_id_seq; Type: SEQUENCE OWNED BY; Schema: assignment3; Owner: postgres
--

ALTER SEQUENCE assignment3.bill_id_seq OWNED BY assignment3.bill.id;


--
-- Name: client; Type: TABLE; Schema: assignment3; Owner: postgres
--

CREATE TABLE assignment3.client (
    id integer NOT NULL,
    name character varying(100)
);


ALTER TABLE assignment3.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: assignment3; Owner: postgres
--

CREATE SEQUENCE assignment3.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE assignment3.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: assignment3; Owner: postgres
--

ALTER SEQUENCE assignment3.client_id_seq OWNED BY assignment3.client.id;


--
-- Name: order; Type: TABLE; Schema: assignment3; Owner: postgres
--

CREATE TABLE assignment3."order" (
    id integer NOT NULL,
    clientid integer,
    productid integer,
    quantity integer,
    total_price integer
);


ALTER TABLE assignment3."order" OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE; Schema: assignment3; Owner: postgres
--

CREATE SEQUENCE assignment3.order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE assignment3.order_id_seq OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE OWNED BY; Schema: assignment3; Owner: postgres
--

ALTER SEQUENCE assignment3.order_id_seq OWNED BY assignment3."order".id;


--
-- Name: product; Type: TABLE; Schema: assignment3; Owner: postgres
--

CREATE TABLE assignment3.product (
    id integer NOT NULL,
    name character varying(100),
    price integer,
    stock integer
);


ALTER TABLE assignment3.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: assignment3; Owner: postgres
--

CREATE SEQUENCE assignment3.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE assignment3.product_id_seq OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: assignment3; Owner: postgres
--

ALTER SEQUENCE assignment3.product_id_seq OWNED BY assignment3.product.id;


--
-- Name: bill id; Type: DEFAULT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.bill ALTER COLUMN id SET DEFAULT nextval('assignment3.bill_id_seq'::regclass);


--
-- Name: client id; Type: DEFAULT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.client ALTER COLUMN id SET DEFAULT nextval('assignment3.client_id_seq'::regclass);


--
-- Name: order id; Type: DEFAULT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3."order" ALTER COLUMN id SET DEFAULT nextval('assignment3.order_id_seq'::regclass);


--
-- Name: product id; Type: DEFAULT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.product ALTER COLUMN id SET DEFAULT nextval('assignment3.product_id_seq'::regclass);


--
-- Data for Name: bill; Type: TABLE DATA; Schema: assignment3; Owner: postgres
--

COPY assignment3.bill (id, orderid, creation_date, total_price) FROM stdin;
1	1	2023-05-19 04:27:03.419388	8
2	2	2023-05-19 04:27:15.347392	80
3	3	2023-05-19 04:27:45.488066	30
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: assignment3; Owner: postgres
--

COPY assignment3.client (id, name) FROM stdin;
1	Ana
2	Bob
4	Carlos
\.


--
-- Data for Name: order; Type: TABLE DATA; Schema: assignment3; Owner: postgres
--

COPY assignment3."order" (id, clientid, productid, quantity, total_price) FROM stdin;
1	2	2	2	8
2	1	4	10	80
3	4	1	6	30
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: assignment3; Owner: postgres
--

COPY assignment3.product (id, name, price, stock) FROM stdin;
2	Apa minerala	4	10
4	Ciocolata alba	8	5
1	Apa plata	5	4
\.


--
-- Name: bill_id_seq; Type: SEQUENCE SET; Schema: assignment3; Owner: postgres
--

SELECT pg_catalog.setval('assignment3.bill_id_seq', 1, false);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: assignment3; Owner: postgres
--

SELECT pg_catalog.setval('assignment3.client_id_seq', 1, false);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: assignment3; Owner: postgres
--

SELECT pg_catalog.setval('assignment3.order_id_seq', 1, false);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: assignment3; Owner: postgres
--

SELECT pg_catalog.setval('assignment3.product_id_seq', 1, false);


--
-- Name: bill bill_pkey; Type: CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.bill
    ADD CONSTRAINT bill_pkey PRIMARY KEY (id);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: order order_pkey; Type: CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3."order"
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: bill bill_orderid_fkey; Type: FK CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3.bill
    ADD CONSTRAINT bill_orderid_fkey FOREIGN KEY (orderid) REFERENCES assignment3."order"(id);


--
-- Name: order order_clientid_fkey; Type: FK CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3."order"
    ADD CONSTRAINT order_clientid_fkey FOREIGN KEY (clientid) REFERENCES assignment3.client(id);


--
-- Name: order order_productid_fkey; Type: FK CONSTRAINT; Schema: assignment3; Owner: postgres
--

ALTER TABLE ONLY assignment3."order"
    ADD CONSTRAINT order_productid_fkey FOREIGN KEY (productid) REFERENCES assignment3.product(id);


--
-- PostgreSQL database dump complete
--

