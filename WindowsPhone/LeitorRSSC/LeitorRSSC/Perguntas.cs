using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Serialization;

namespace LeitorRSSC
{
    public class Perguntas
    {
        // Lista de Perguntas
        [XmlElement("Pergunta")]
        public List<Pergunta> TodasPerguntas { get; set; }
    }

    public class Pergunta
    {
        public int Id { get; set; }
        public string Enunciado { get; set; }
        public string Alternativa_A { get; set; }
        public string Alternativa_B { get; set; }
        public string Alternativa_C { get; set; }
        public string Alternativa_D { get; set; }
        public string Resposta { get; set; }
    }
}
