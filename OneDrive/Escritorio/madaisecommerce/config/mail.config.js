const Resend = require('resend').Resend;

const resend = new Resend('re_Af59B1WC_PZ54SZQ5uEcp3cuWSb5BKhE7');

const sendEmail = async (email, subject, html) => {
    try {
      const { data, error } = await resend.emails.send({
        from: 'Acme <onboarding@resend.dev>',
        to: [email],
        subject,
        html,
      });

      if (error) {
        return console.error({ error });
      }

      console.log({ data });
      console.log('Correo enviado con éxito');
      
    } catch (error) {
      console.log('Algo no va bien con el email', error);
    }
}

const getTemplate = (name, token) => {
  return `
    <head>
      <link rel="stylesheet" href="./style.css">
    </head>
    
    <div id="email___content">
      <img src="https://i.imgur.com/eboNR82.png" alt="">
      <h2>Hola ${ name }</h2>
      <p>Para confirmar tu cuenta, ingresa al siguiente enlace</p>
      <a href="http://localhost:4000/api/user/confirm/${ token }" target="_blank">
        Confirmar Cuenta
      </a>
    </div>
  `;
}

module.exports = {
  sendEmail,
  getTemplate
}
